package com.FACTor.Digital_Wallet.controller;

import com.FACTor.Digital_Wallet.dto.WalletDTO;
import com.FACTor.Digital_Wallet.entity.Wallet;
import com.FACTor.Digital_Wallet.service.WalletLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class WalletController {
@Autowired
private WalletLogic logic;
    @PostMapping("/new-wallet")
    public ResponseEntity<Wallet> newWallet(@RequestParam String email, @RequestBody WalletDTO pass){
        Wallet data = logic.newWallet(email, pass);
        return ResponseEntity.ok(data);
    }
    @GetMapping("/account-details")
    public ResponseEntity<Optional<Wallet>> accountDetails(@RequestParam long accountNumber){
       Optional<Wallet> account = logic.accountDetails(accountNumber);
        return ResponseEntity.ok(account);
    }
}
