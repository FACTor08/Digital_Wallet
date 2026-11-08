package com.FACTor.Digital_Wallet.customer;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomerMapper {

    public Customer userTransfer(Map<String, Object> photo, CustomerDTO data){

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
            customer.setImage((String) photo.get("secure_url"));
            customer.setImageId((String) photo.get("public_id"));

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
    public CustomerResponseDTO responseDTO(Customer customer){
        return CustomerResponseDTO.builder()
                .id(customer.getId())
                .firstname(customer.getFirstname())
                .otherName(customer.getOtherName())
                .lastname(customer.getLastname())
                .username(customer.getUsername())
                .accountTier(customer.getAccountTier())
                .image(customer.getImage())
                .build();
    }
}
