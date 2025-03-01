package org.example.invalidtx.controller;

import org.example.invalidtx.entity.Transfer;
import org.example.invalidtx.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping
    public ResponseEntity<Transfer> makeTransfer(@RequestParam String fromAccount,
                                                 @RequestParam String toAccount,
                                                 @RequestParam Integer amount) {
        try {
            Transfer transfer = transferService.makeTransfer(fromAccount, toAccount, amount);
            return ResponseEntity.ok(transfer);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Return 400 Bad Request
        }
    }

}
