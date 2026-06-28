package com.FACTor.Digital_Wallet.mapper;

import com.FACTor.Digital_Wallet.dto.CustomerDTO;

import com.FACTor.Digital_Wallet.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer userTransfer(CustomerDTO data){

              Customer customer = new Customer();

            customer.setOtherName(data.getOtherName());
            customer.setPhone(data.getPhone());
            customer.setEmail(data.getEmail());
            customer.setDob(data.getDob());
            customer.setAddress(data.getAddress());
            customer.setBvn(data.getBvn());
            customer.setNin(data.getNin());
            customer.setUsername(data.getUsername());
            if (data.getFirstname().isEmpty() || data.getLastname().isEmpty()) {
                throw new IllegalArgumentException("Please enter firstname/lastname");
            }
            customer.setFirstname(data.getFirstname());
            customer.setLastname(data.getLastname());

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
