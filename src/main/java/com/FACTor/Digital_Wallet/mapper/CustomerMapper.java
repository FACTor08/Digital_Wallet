package com.FACTor.Digital_Wallet.mapper;

import com.FACTor.Digital_Wallet.dto.CustomerDTO;

import com.FACTor.Digital_Wallet.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CustomerMapper {

private final Random random = new Random();

    public Customer userTransfr(CustomerDTO data, byte[] image, String imageType){

              Customer customer = new Customer();

            customer.setFirstname(data.getFirstname());
            customer.setLastname(data.getLastname());
            customer.setOtherName(data.getOtherName());
            customer.setPhone(data.getPhone());
            customer.setEmail(data.getEmail());
            customer.setImage(image);
            customer.setImageType(imageType);
            customer.setDob(data.getDob());
            customer.setAddress(data.getAddress());
            customer.setBvn(data.getBvn());
            customer.setNin(data.getNin());
            customer.setUsername(data.getUsername());

            boolean ninExists, bvnExists;
            bvnExists = data.getBvn() != 0;
            ninExists = data.getNin() != 0;

            if (bvnExists && ninExists) {
                customer.setAccountTier("Tier 3");
            } else if (bvnExists || ninExists) {
                customer.setAccountTier("Tier 2");
            } else {
                customer.setAccountTier("Tier 1");
            }
        return customer;
    }
}
