package com.FACTor.Digital_Wallet.wallet;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record WalletResponseDTO(
         long id,
         long accountNumber,
         String firstname,
         String lastname,
         String otherName,
         String accountTier,
         BigDecimal balance
) {
}
