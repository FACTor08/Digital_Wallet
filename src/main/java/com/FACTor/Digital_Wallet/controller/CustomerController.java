package com.FACTor.Digital_Wallet.controller;

import com.FACTor.Digital_Wallet.dto.CustomerDTO;
import com.FACTor.Digital_Wallet.service.CustomerLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerLogic logic;

    @PostMapping(value = "/user-signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addUser(@ModelAttribute CustomerDTO data,
                                          @RequestPart(name = "image") MultipartFile image) throws IOException {
        String msg = logic.addUser(data, image);
        return ResponseEntity.ok(msg);
    }
}
