package com.FACTor.Digital_Wallet.exceptions;

//throws 401 unauthorized error status code
public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(String message){
        super(message);
    }
}
