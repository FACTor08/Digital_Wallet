package com.FACTor.Digital_Wallet.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerLogic logic;

    @PostMapping("/user-signup")
    public ResponseEntity<CustomerResponseDTO> addUser(@RequestBody CustomerDTO data) {
        CustomerResponseDTO register = logic.addUser(data);
        return ResponseEntity.ok(register);
    }

    @PostMapping(value = "/profile-picture-upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CustomerResponseDTO> uploadProfile(Authentication auth,
                                                             @ModelAttribute(name = "image") MultipartFile image) throws IOException {
    String user = auth.getName();
        CustomerResponseDTO data = logic.addUserImage(user, image);
        return ResponseEntity.ok(data);
    }
}

