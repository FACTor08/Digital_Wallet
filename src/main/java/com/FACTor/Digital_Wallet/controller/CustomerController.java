package com.FACTor.Digital_Wallet.controller;

import com.FACTor.Digital_Wallet.dto.CustomerDTO;
import com.FACTor.Digital_Wallet.entity.Customer;
import com.FACTor.Digital_Wallet.service.CustomerLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerLogic logic;

    @PostMapping("/user-signup")
    public ResponseEntity<Customer> addUser(@RequestBody CustomerDTO data) {
        Customer register = logic.addUser(data);
        return ResponseEntity.ok(register);
    }

    @PostMapping(value = "/profile-picture-upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Customer> uploadProfile(@ModelAttribute String user,
                                                @RequestPart(name = "image") MultipartFile image) throws IOException {

        Customer data = logic.addUserImage(user, image);
        return ResponseEntity.ok(data);
    }   
}
