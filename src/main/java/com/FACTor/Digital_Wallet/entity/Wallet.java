package com.FACTor.Digital_Wallet.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
