package com.FACTor.Digital_Wallet.Login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class LoginRequest {
    private String username;
    private String passcode;
}
