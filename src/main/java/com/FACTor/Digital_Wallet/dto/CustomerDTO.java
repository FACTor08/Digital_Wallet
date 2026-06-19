package com.FACTor.Digital_Wallet.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CustomerDTO {

    private String firstname;
    private String lastname;
    private String otherName;
    private String phone;
    private String username;
    private String email;
    private long nin;
    private long bvn;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dob;
    private String address;
}
