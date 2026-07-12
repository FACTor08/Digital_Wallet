package com.FACTor.Digital_Wallet.service;

import com.FACTor.Digital_Wallet.dto.LoginRequest;
import com.FACTor.Digital_Wallet.entity.Customer;
import com.FACTor.Digital_Wallet.entity.Wallet;
import com.FACTor.Digital_Wallet.exceptions.InvalidCredentialsException;
import com.FACTor.Digital_Wallet.repository.CustomerRepo;
import com.FACTor.Digital_Wallet.repository.WalletRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginLogic {
    private final CustomerRepo repo;
    private final WalletRepo walletRepo;
    private final PasswordEncoder encode;

    @Transactional
    public Customer login(LoginRequest request){

        //searches for user's username in the db
        Customer customer = repo.findByUsername(request.getUsername())
                .orElseThrow(() -> new InvalidCredentialsException("Wrong Username/Passcode entry"));

        //searches for existing user wallet
        Wallet wallet = walletRepo.findByCustomer(customer)
                .orElseThrow(()-> new InvalidCredentialsException("User Wallet not Found"));

        //ensures the entered passcode matches the passcode in the db
        if (!encode.matches(request.getPasscode(), wallet.getPasscode())){
    throw new InvalidCredentialsException("Wrong Username/Passcode entry");
    }
        return customer;
        }
}
