package com.FACTor.Digital_Wallet.controller;

import com.FACTor.Digital_Wallet.service.TransactionLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class TransactionController {
private final TransactionLogic logic;

@PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestParam long accountNumber,@RequestParam BigDecimal amount){
    String transaction = logic.deposit(accountNumber,amount);

    return ResponseEntity.ok(transaction);
}
    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam long senderAccount, @RequestParam long receiverAccount, @RequestParam BigDecimal amount) {
        String transaction = logic.transfer(senderAccount, receiverAccount, amount);

        return ResponseEntity.ok(transaction);
    }
}
