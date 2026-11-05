package com.FACTor.Digital_Wallet.Login;

import com.FACTor.Digital_Wallet.customer.Customer;
import com.FACTor.Digital_Wallet.wallet.Wallet;
import com.FACTor.Digital_Wallet.customer.CustomerRepo;
import com.FACTor.Digital_Wallet.wallet.WalletRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
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
                .orElseThrow(() -> new BadCredentialsException("Wrong Username/Passcode entry"));

        //searches for existing user wallet
        Wallet wallet = walletRepo.findByCustomer(customer)
                .orElseThrow(()-> new BadCredentialsException("User Wallet not Found"));

        //ensures the entered passcode matches the passcode in the db
        if (!encode.matches(request.getPasscode(), wallet.getPasscode())){
    throw new BadCredentialsException("Wrong Username/Passcode entry");
    }
        return customer;
        }
}
