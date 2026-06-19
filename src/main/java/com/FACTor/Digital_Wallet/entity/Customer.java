package com.FACTor.Digital_Wallet.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String accountTier;
@NotNull(message = "Enter your Firstname")
    private String firstname;
@NotNull(message = "Enter your Lastname")
    private String lastname;
    private String otherName;
    private String username;
private String phone;
@NotNull
    private String email;
    @Lob
    private byte[] image;
    private String imageType;
    private long nin;
    private long bvn;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dob;
@NotNull(message = "Input a valid address")
    private String address;
}
