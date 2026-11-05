package com.FACTor.Digital_Wallet.customer;

import com.FACTor.Digital_Wallet.wallet.WalletDTO;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerDetails implements UserDetailsService {
    private final CustomerRepo repo;

    @NotNull
    @Transactional
    @Override
    public UserDetails loadUserByUsername(@NotNull String username){
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
