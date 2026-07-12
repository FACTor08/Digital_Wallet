package com.FACTor.Digital_Wallet.exceptions;

//throws 404 not found error status code
public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String message){
        super(message);
    }
}
