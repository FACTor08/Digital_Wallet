package com.FACTor.Digital_Wallet.wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WalletController {
@Autowired
private WalletLogic logic;
    @PostMapping("/new-wallet")
    public ResponseEntity<WalletResponseDTO> newWallet(@RequestParam String email, @RequestBody WalletDTO pass){
        WalletResponseDTO data = logic.newWallet(email, pass);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/account-details")
    public ResponseEntity<WalletResponseDTO> accountDetails(@RequestParam long accountNumber){
        WalletResponseDTO account = logic.accountDetails(accountNumber);
        return ResponseEntity.ok(account);
    }
}
