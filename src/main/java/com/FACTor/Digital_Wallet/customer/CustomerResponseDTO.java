package com.FACTor.Digital_Wallet.customer;

import lombok.Builder;

@Builder
public record CustomerResponseDTO(
         long id,
         String accountTier,
         String firstname,
         String lastname,
         String otherName,
         String username,
          byte[] image
         ) {
}
