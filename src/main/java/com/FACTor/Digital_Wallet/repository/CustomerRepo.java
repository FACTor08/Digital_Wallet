package com.FACTor.Digital_Wallet.repository;

import com.FACTor.Digital_Wallet.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByBvn(long bvn);
    Optional<Customer> findByNin(long nin);
    Optional<Customer> findByUsername(String username);
    Optional<Customer> findByFirstname(String firstname);
}
