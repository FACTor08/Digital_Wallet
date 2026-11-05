package com.FACTor.Digital_Wallet.wallet;

import com.FACTor.Digital_Wallet.customer.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long accountNumber;
    private BigDecimal balance;
    private LocalDate createdAt;
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String passcode;
}
