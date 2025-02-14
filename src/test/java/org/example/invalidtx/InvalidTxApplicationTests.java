package org.example.invalidtx;

import org.example.invalidtx.entity.Transfer;
import org.example.invalidtx.mapper.TransferMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InvalidTxApplicationTests {

    @Autowired
    private TransferMapper transferMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void testSelectTransferById() {
        Transfer transfer = transferMapper.selectTransferById(1);
        System.out.println(transfer);
    }
}
