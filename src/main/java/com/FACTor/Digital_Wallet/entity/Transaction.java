package com.FACTor.Digital_Wallet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
    private BigDecimal amount;
    private String reference;
    private String type;   //debit/credit
    private LocalDateTime timestamp;

    @JoinColumn(name = "wallet_id")
    @ManyToOne
    private Wallet wallet;
}
