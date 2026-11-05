package com.FACTor.Digital_Wallet.wallet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class WalletDTO {

    private String passcode;
    private String confirmPasscode;
}
