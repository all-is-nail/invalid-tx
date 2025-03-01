package org.example.invalidtx.service;

import org.example.invalidtx.entity.Transfer;
import org.example.invalidtx.mapper.TransferMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferService {

    @Autowired
    private TransferMapper transferMapper;

    @Transactional
    public Transfer makeTransfer(String fromAccount, String toAccount, Integer amount) {
        if (fromAccount == null || toAccount == null || amount <= 0) {
            throw new IllegalArgumentException("Invalid transfer details");
        }
        
        Transfer transfer = new Transfer();
        transfer.setFromAccount(fromAccount);
        transfer.setToAccount(toAccount);
        transfer.setAmount(amount);
        transferMapper.insertTransfer(transfer);
        return transfer;
    }

    public Transfer selectTransferById(long id) {
        return transferMapper.selectTransferById(id);
    }
}
