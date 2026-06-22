package com.FACTor.Digital_Wallet.service;


import com.FACTor.Digital_Wallet.entity.Customer;
import com.FACTor.Digital_Wallet.entity.Transaction;
import com.FACTor.Digital_Wallet.entity.Wallet;
import com.FACTor.Digital_Wallet.exceptions.ResourceNotFoundException;
import com.FACTor.Digital_Wallet.repository.CustomerRepo;
import com.FACTor.Digital_Wallet.repository.TransactionRepo;
import com.FACTor.Digital_Wallet.repository.WalletRepo;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionLogic {
    private final WalletRepo walletRepo;
    private final TransactionRepo transactionRepo;
    private final CustomerRepo customerRepo;

public String generateRef(){
    return "TRX-" + System.currentTimeMillis(); //generates transaction reference code
    }
    //searches for user account number in the db and throws "404 not found" if not found
public String deposit(long accountNumber, BigDecimal amount){
   Optional<Wallet> wallet = walletRepo.findByAccountNumber(accountNumber);
   if(wallet.isEmpty()){
       throw new ResourceNotFoundException("Invalid Account Number!");
   }

   //deposit
    Wallet balance = wallet.get();
    BigDecimal expectedDeposit = depositByTierStatus(amount, balance);
    balance.setBalance(expectedDeposit);
    walletRepo.save(balance);

    Transaction receipt = new Transaction();
    receipt.setAmount(amount);
    receipt.setType("CREDIT");
    receipt.setReference(generateRef());
    receipt.setWallet(balance);
    receipt.setTimestamp(LocalDateTime.now());
    transactionRepo.save(receipt);

    return "CREDIT ALERT: You have been credited with $" + amount + "\n " +
                "Your new balance is $" + balance.getBalance();
}

    @NotNull
    private static BigDecimal depositByTierStatus(BigDecimal amount, Wallet balance) {
        Customer customer = balance.getCustomer();
        BigDecimal expectedDeposit = balance.getBalance().add(amount);
        String tier = customer.getAccountTier();

        if (tier.equals("Tier 1") && expectedDeposit.compareTo(new BigDecimal(300000))> 0){
            throw new IllegalArgumentException("Deposit amount exceeds current account tier (max: $300000)");
        } else if (tier.equals("Tier 2") && expectedDeposit.compareTo(new BigDecimal(750000)) > 0){
            throw new IllegalArgumentException("Deposit amount exceeds current account tier (max: $750000)");
        }
        return expectedDeposit;
    }

    public String transfer(long senderAccount, long receiverAccount, BigDecimal amount){

    //searches for both sender and receiver account numbers in the db or throws 404 if not found
    Optional<Wallet> send = walletRepo.findByAccountNumber(senderAccount);
    Optional<Wallet> receive = walletRepo.findByAccountNumber(receiverAccount);
    if(send.isEmpty() || receive.isEmpty()){
        throw new ResourceNotFoundException("Invalid Account Number");
    }
    Wallet sender = send.get();
    Wallet receiver = receive.get();

//checks if sender has enough funds to send out
    if (sender.getBalance().compareTo(amount) < 0){
        throw new IllegalArgumentException("Insufficient Balance");
    }

    sender.setBalance(sender.getBalance().subtract(amount));
    receiver.setBalance(receiver.getBalance().add(amount));

    //debit receipt for the sender
    Transaction senderReceipt = new Transaction();
    senderReceipt.setReference(generateRef());
    senderReceipt.setType("DEBIT");
    senderReceipt.setAmount(amount);
    senderReceipt.setWallet(sender);
    senderReceipt.setTimestamp(LocalDateTime.now());

    //credit receipt for the receiver
    Transaction receiverReceipt = new Transaction();
    receiverReceipt.setReference(generateRef());
    receiverReceipt.setType("CREDIT");
    receiverReceipt.setAmount(amount);
    receiverReceipt.setWallet(receiver);
    receiverReceipt.setTimestamp(LocalDateTime.now());

    transactionRepo.saveAll(List.of(senderReceipt, receiverReceipt));//saves receipts to the db

    String fullName = receiver.getCustomer().getLastname() + " " + receiver.getCustomer().getFirstname();
//user receipt in json
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("Reference: ", senderReceipt.getReference());
    body.put("Type: ", "DEBIT");
    body.put("Amount: ", amount);
    body.put("Receiver: ", fullName);
    body.put("Time: ", LocalDateTime.now());

    return "TRANSACTION SUCCESSFUL! \n Receipt: \n" + body;
}

   }
