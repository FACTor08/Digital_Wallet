package com.FACTor.Digital_Wallet.service;

import com.FACTor.Digital_Wallet.dto.WalletDTO;
import com.FACTor.Digital_Wallet.entity.Customer;
import com.FACTor.Digital_Wallet.entity.Wallet;
import com.FACTor.Digital_Wallet.exceptions.AccountNotFoundException;
import com.FACTor.Digital_Wallet.exceptions.ExistingUserException;
import com.FACTor.Digital_Wallet.exceptions.InvalidCredentialsException;
import com.FACTor.Digital_Wallet.repository.CustomerRepo;
import com.FACTor.Digital_Wallet.repository.WalletRepo;
import com.FACTor.Digital_Wallet.mapper.WalletMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class WalletLogic {
private final WalletRepo repo;
private final CustomerRepo customerRepo;
private final WalletMapper set;

    public Wallet newWallet(String email, WalletDTO pass) {
        //searches for user email in the db
        Optional<Customer> user = customerRepo.findByEmail(email);
        if (user.isEmpty()){
            throw new InvalidCredentialsException("Invalid email!: Please confirm email and input a valid email");
        }
        //prompts user to ensure passcode entry is certain
        if (!Objects.equals(pass.getPasscode(), pass.getConfirmPasscode())){
            throw new IllegalArgumentException("Passcode doesn't match!");
        }
        Customer customer = user.get();
        // throws 409 error if a user has an existing wallet
        if(repo.existsByCustomer(customer)) {
            throw new ExistingUserException("User already has an existing wallet");
        }else {
            //creates new wallet if user meets all conditions
            Wallet wallet = set.transfer(pass);
            wallet.setCustomer(customer);
    return repo.save(wallet);  //saves new user data to the db
           }
    }
    public Optional<Wallet> accountDetails(long accountNumber) {
    Optional<Wallet> data = repo.findByAccountNumber(accountNumber);
                if(data.isPresent()) {
                    return data;
                }
       throw new AccountNotFoundException("Invalid account number!");
    }
}
