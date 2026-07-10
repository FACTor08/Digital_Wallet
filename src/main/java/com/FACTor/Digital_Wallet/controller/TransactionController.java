package com.FACTor.Digital_Wallet.controller;

import com.FACTor.Digital_Wallet.entity.Transaction;
import com.FACTor.Digital_Wallet.service.TransactionLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransactionController {
private final TransactionLogic logic;

@PostMapping("/deposit")
    public ResponseEntity<Transaction> deposit(@RequestParam long accountNumber,@RequestParam BigDecimal amount){
    Transaction transaction = logic.deposit(accountNumber,amount);

    return ResponseEntity.ok(transaction);
}
    @PostMapping("/transfer")
    public ResponseEntity<List<Transaction>> transfer(@RequestParam long senderAccount, @RequestParam long receiverAccount, @RequestParam BigDecimal amount) {
        List<Transaction> transaction = logic.transfer(senderAccount, receiverAccount, amount);

        return ResponseEntity.ok(transaction);
    }
}
