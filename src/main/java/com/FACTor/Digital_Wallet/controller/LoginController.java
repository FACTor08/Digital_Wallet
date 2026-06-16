package com.FACTor.Digital_Wallet.controller;

import com.FACTor.Digital_Wallet.config.JwtUtil;
import com.FACTor.Digital_Wallet.dto.LoginRequest;
import com.FACTor.Digital_Wallet.entity.Customer;
import com.FACTor.Digital_Wallet.service.LoginLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginLogic logic;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){

        Customer customer = logic.login(request);

        return jwtUtil.generateToken(customer);
    }

}
