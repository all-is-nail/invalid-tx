package org.example.invalidtx.controller;

import org.example.invalidtx.entity.Transfer;
import org.example.invalidtx.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping
    public Transfer makeTransfer(@RequestParam String fromAccount,
                                 @RequestParam String toAccount,
                                 @RequestParam Integer amount) {
        return transferService.makeTransfer(fromAccount, toAccount, amount);
    }

}
