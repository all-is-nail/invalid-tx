package org.example.invalidtx;

import org.example.invalidtx.entity.Transfer;
import org.example.invalidtx.service.TransferService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransactionManageTest {

    @Autowired
    private TransferService transferService;

    @Autowired
    private TransactionTemplate transactionTemplate;

    /**
     * Test case to verify that a transaction is rolled back when an exception occurs.
     */
    @Test
    public void testTransactionRollBack() {
        AtomicReference<Long> id = new AtomicReference<>();
        transactionTemplate.execute(status -> {
            try {
                // Simulate a transaction
                Transfer transfer = transferService.makeTransfer("A", "B", 10000);
                id.set(transfer.getId());
                // Simulate an exception to trigger rollback
                throw new RuntimeException("Simulated exception");
            } catch (Exception e) {
                System.out.println("Transaction rolled back due to: " + e.getMessage());
                // Rollback the transaction
                status.setRollbackOnly();
            }
            return null;
        });

        // After the transaction, check if the transfer was rolled back
        transactionTemplate.execute(status -> {
            Transfer transfer = transferService.selectTransferById(id.get());
            assertNull(transfer, "Transfer should be rolled back");
            return null;
        });
    }

    /**
     * Test case to configure a declarative transaction and make sure it commits.
     */
    @Test
    @Transactional
    public void testConfigDeclarativeTransactionCommit() {
        // in test context,default transaction for test method is rollback
        // but in this case,use TestTransaction to control transaction to commit
        TestTransaction.flagForCommit();
        // Create first transfer
        Transfer transfer = transferService.makeTransfer("Account1", "Account2", 1000);
        assertNotNull(transfer.getId(), "Transfer 1 should have an ID");
    }
}
