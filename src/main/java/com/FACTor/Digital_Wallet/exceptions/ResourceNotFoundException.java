package com.FACTor.Digital_Wallet.exceptions;

//throws 404 not found error status code
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
