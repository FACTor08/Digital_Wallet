package com.FACTor.Digital_Wallet.wallet;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class WalletMapper {
    private final PasswordEncoder encoder;
    Random random = new Random();
    public long accountNumber(){
        return random.nextLong(1000000000L, 2000000000L);
    }

    public Wallet transfer(WalletDTO walletDTO){
        Wallet wallet = new Wallet();

        wallet.setAccountNumber(accountNumber());
        wallet.setBalance(BigDecimal.ZERO);
        wallet.setCreatedAt(LocalDate.now());

        if(walletDTO.getPasscode() == null || walletDTO.getPasscode().length() != 6){
            throw new IllegalArgumentException("6 digit passcode required!");
        }
        wallet.setPasscode(encoder.encode(walletDTO.getPasscode()));
    return wallet;
        }
    public WalletResponseDTO responseDTO(Wallet wallet){
        return WalletResponseDTO.builder()
                .id(wallet.getId())
                .accountNumber(accountNumber())
                .firstname(wallet.getCustomer().getFirstname())
                .otherName(wallet.getCustomer().getOtherName())
                .lastname(wallet.getCustomer().getLastname())
                .balance(wallet.getBalance())
                .accountTier(wallet.getCustomer().getAccountTier())
                .build();
    }
}
