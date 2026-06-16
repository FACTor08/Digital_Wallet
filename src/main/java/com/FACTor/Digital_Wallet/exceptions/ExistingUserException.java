package com.FACTor.Digital_Wallet.exceptions;

//throws 409 conflict error status code
public class ExistingUserException extends RuntimeException{
    public ExistingUserException(String message){
        super(message);
    }
}
