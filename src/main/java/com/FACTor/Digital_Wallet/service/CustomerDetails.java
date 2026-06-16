package com.FACTor.Digital_Wallet.service;

import com.FACTor.Digital_Wallet.dto.WalletDTO;
import com.FACTor.Digital_Wallet.entity.Customer;
import com.FACTor.Digital_Wallet.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerDetails implements UserDetailsService {
    private final CustomerRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username){
        Customer user = repo.findByUsername(username)     //searches for user in the db
                .orElseThrow(()-> new UsernameNotFoundException("Username not Found!!!"));

        WalletDTO wallet = new WalletDTO();

        //user verification using username and password
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(wallet.getPasscode())
                .build();
    }
}
