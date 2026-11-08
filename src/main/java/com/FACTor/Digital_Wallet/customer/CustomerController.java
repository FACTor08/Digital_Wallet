package com.FACTor.Digital_Wallet.customer;

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

    @PostMapping(value ="/user-signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CustomerResponseDTO> addUser(@ModelAttribute CustomerDTO data,
                                                       @RequestPart MultipartFile file) throws IOException {
        CustomerResponseDTO register = logic.addUser(file,data);
        return ResponseEntity.ok(register);
    }

}

