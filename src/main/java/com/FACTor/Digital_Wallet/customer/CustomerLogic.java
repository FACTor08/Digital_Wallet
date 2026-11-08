package com.FACTor.Digital_Wallet.customer;

import com.FACTor.Digital_Wallet.exceptions.ExistingUserException;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor

public class CustomerLogic {
    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    private final Cloudinary cloudinary;

    public CustomerResponseDTO addUser(MultipartFile image, CustomerDTO data) throws IOException {
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
        Map<String, Object> imageFinal = cloudinary.uploader().upload(image, ObjectUtils.asMap(
            "resource_type","image",
                "folder","wallet/image"
        ));
        Customer customer = mapper.userTransfer(imageFinal, data);
     Customer saved = repo.save(customer); //save user data to the db
    return mapper.responseDTO(saved);
        }

}
