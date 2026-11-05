package com.FACTor.Digital_Wallet.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionLogic logic;

    @PostMapping("/deposit")
    // deposit added for adding sake.....(it's an online wallet system to send and receive funds)
    public ResponseEntity<TransactionResponseDTO> deposit(@RequestParam long accountNumber, @RequestParam BigDecimal amount) {
        TransactionResponseDTO transaction = logic.deposit(accountNumber, amount);

        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponseDTO> transfer(Authentication authentication,
                                                           @RequestParam long receiverAccount,
                                                           @RequestParam BigDecimal amount,
                                                           @RequestParam String passcode) {
        String sender = authentication.getName();
        TransactionResponseDTO transaction = logic.transfer(sender, receiverAccount, amount, passcode);

        return ResponseEntity.ok(transaction);
    }

}
