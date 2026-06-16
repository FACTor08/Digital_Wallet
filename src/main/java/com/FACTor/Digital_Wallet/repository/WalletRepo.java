package com.FACTor.Digital_Wallet.repository;

import com.FACTor.Digital_Wallet.entity.Customer;
import com.FACTor.Digital_Wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepo extends JpaRepository<Wallet, Long> {
    Boolean existsByCustomer(Customer user);
    Optional<Wallet> findByAccountNumber(long account);
    Optional<Wallet> findByCustomer(Customer customer);
}
