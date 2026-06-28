package com.FACTor.Digital_Wallet.controller;

import com.FACTor.Digital_Wallet.dto.CustomerDTO;
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
    public ResponseEntity<String> addUser(@RequestBody CustomerDTO data) {
        String msg = logic.addUser(data);
        return ResponseEntity.ok(msg);
    }

    @PostMapping(value = "/profile-picture-upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadProfile(@ModelAttribute String user,
                                                @RequestPart(name = "image") MultipartFile image) throws IOException {

        String msg = logic.addUserImage(user, image);
        return ResponseEntity.ok(msg);
    }   
}
