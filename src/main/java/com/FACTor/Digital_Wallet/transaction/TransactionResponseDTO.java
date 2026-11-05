package com.FACTor.Digital_Wallet.transaction;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record TransactionResponseDTO(
         long id,
         BigDecimal amount,
         String reference,
         String type,
         LocalDateTime timestamp,
         BigDecimal wallet
) {
}
