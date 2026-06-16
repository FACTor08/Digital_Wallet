package com.FACTor.Digital_Wallet.repository;

import com.FACTor.Digital_Wallet.entity.Transaction;
import com.FACTor.Digital_Wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findByWallet(Wallet wallet);
}
