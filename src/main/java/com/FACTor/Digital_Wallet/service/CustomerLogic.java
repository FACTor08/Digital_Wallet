package com.FACTor.Digital_Wallet.service;

import com.FACTor.Digital_Wallet.dto.CustomerDTO;
import com.FACTor.Digital_Wallet.entity.Customer;
import com.FACTor.Digital_Wallet.exceptions.ExistingUserException;
import com.FACTor.Digital_Wallet.mapper.CustomerMapper;
import com.FACTor.Digital_Wallet.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public Customer addUser(CustomerDTO data){
        Optional<Customer> checkEmail = repo.findByEmail(data.getEmail());
        Optional<Customer> checkBvn = repo.findByBvn(data.getBvn());
        Optional<Customer> checkNin = repo.findByNin(data.getNin());

        // makes sure bvn,nin and phone are either valid(11 digits) or not provided
        boolean invalidBvn, invalidNin, invalidPhone;
        invalidBvn = data.getBvn() != 0 && String.valueOf(data.getBvn()).length() != 11;
        invalidNin = data.getNin() != 0 && String.valueOf(data.getNin()).length() != 11;
        invalidPhone = data.getPhone() != null && data.getPhone().length() != 11;
        if (invalidBvn || invalidNin || invalidPhone) {
            throw new IllegalArgumentException("Please Confirm your NIN/BVN/Phone-number is correct!");
        }
        // throws error code 409 If email,bvn or nin already exists or else creates a new account
        if (checkEmail.isPresent() && checkBvn.isPresent() && checkNin.isPresent()) {
            throw new ExistingUserException("User already Exists!");
        }

        Customer customer = mapper.userTransfer(data);
     return repo.save(customer); //save user data to the db

        }

    //A method for user to upload profile picture
    public Customer addUserImage(String user, MultipartFile file) throws IOException {
        Optional<Customer> username = repo.findByUsername(user);
        Optional<Customer> firstname  = repo.findByFirstname(user);
        if (username.isEmpty() && firstname.isEmpty()) {
            throw new UsernameNotFoundException("User Not Found");
        }
        Customer update = username.get();
        byte[] image = file.getBytes();
        String imageType = file.getContentType();
        update.setImage(image);
        update.setImageType(imageType);
       return repo.save(update);
    }
}
