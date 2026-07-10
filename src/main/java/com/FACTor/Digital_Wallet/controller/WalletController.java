package com.FACTor.Digital_Wallet.controller;

import com.FACTor.Digital_Wallet.dto.WalletDTO;
import com.FACTor.Digital_Wallet.entity.Wallet;
import com.FACTor.Digital_Wallet.service.WalletLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WalletController {
@Autowired
private WalletLogic logic;
    @PostMapping("/new-wallet")
    public ResponseEntity<Wallet> newWallet(@RequestParam String email, @RequestBody WalletDTO pass){
        Wallet data = logic.newWallet(email, pass);
        return ResponseEntity.ok(data);
    }
}
