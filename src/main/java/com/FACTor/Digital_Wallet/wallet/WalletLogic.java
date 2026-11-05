package com.FACTor.Digital_Wallet.wallet;

import com.FACTor.Digital_Wallet.customer.Customer;
import com.FACTor.Digital_Wallet.exceptions.AccountNotFoundException;
import com.FACTor.Digital_Wallet.exceptions.ExistingUserException;
import com.FACTor.Digital_Wallet.exceptions.InvalidCredentialsException;
import com.FACTor.Digital_Wallet.customer.CustomerRepo;
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
private final WalletMapper mapper;

    public WalletResponseDTO newWallet(String email, WalletDTO pass) {
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
            Wallet wallet = mapper.transfer(pass);
            wallet.setCustomer(customer);
             Wallet newWallet = repo.save(wallet);  //saves new user data to the db
        return mapper.responseDTO(newWallet);
        }
    }
    public WalletResponseDTO accountDetails(long accountNumber) {
            Wallet data = repo.findByAccountNumber(accountNumber).orElseThrow(()->
                    new AccountNotFoundException("Invalid account number!"));
            return mapper.responseDTO(data);
    }
}
