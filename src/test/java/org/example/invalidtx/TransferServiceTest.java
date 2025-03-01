package org.example.invalidtx;

import org.example.invalidtx.entity.Transfer;
import org.example.invalidtx.service.TransferService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class TransferServiceTest {

    @Autowired
    private TransferService transferService;

    @Test
    void testMakeTransfer() {
        Transfer transfer = transferService.makeTransfer("A", "B", 100);
        System.out.println(transfer);

        transfer = transferService.selectTransferById(transfer.getId());
        System.out.println(transfer);
    }

    @Test
    void testMakeTransferInvalidAmount() {
        assertThrows(IllegalArgumentException.class, () -> {
            transferService.makeTransfer("A", "B", -100);
        });
    }
}
