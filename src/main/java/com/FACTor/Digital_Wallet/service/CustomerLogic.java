package com.FACTor.Digital_Wallet.service;

import com.FACTor.Digital_Wallet.dto.CustomerDTO;
import com.FACTor.Digital_Wallet.entity.Customer;
import com.FACTor.Digital_Wallet.exceptions.ExistingUserException;
import com.FACTor.Digital_Wallet.mapper.CustomerMapper;
import com.FACTor.Digital_Wallet.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor

public class CustomerLogic {
    private final CustomerRepo repo;
    private final CustomerMapper mapper;

   public String addUser(CustomerDTO data, MultipartFile file) throws IOException {
Optional<Customer> checkEmail = repo.findByEmail(data.getEmail());
Optional<Customer> checkBvn = repo.findByBvn(data.getBvn());
Optional<Customer> checkNin = repo.findByNin(data.getNin());

       boolean bvn, nin, phone;
       bvn = String.valueOf(data.getBvn()).length() != 11;
       nin = String.valueOf(data.getNin()).length() != 11;
       phone = data.getPhone().length() != 11;
       if (bvn||nin||phone){
           throw new IllegalArgumentException("Please Confirm your NIN/BVN/Phone-number is correct!");
       }
       // throws error code 409 If email,bvn or nin already exists or else creates a new account

if (checkEmail.isPresent() || checkBvn.isPresent() ||checkNin.isPresent()){
     throw new ExistingUserException("User already Exists!");
}
       byte[] image = file.getBytes();
       String imageType = file.getContentType();
       Customer customer = mapper.userTransfr(data, image, imageType);

       repo.save(customer); //save user data to the db
       return "Hey " + data.getFirstname() + " your new Wallet is almost ready🤗, Thank you for choosing us❤" ;
       }
   }
