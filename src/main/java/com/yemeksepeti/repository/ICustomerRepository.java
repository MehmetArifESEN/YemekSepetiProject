package com.yemeksepeti.repository;

import com.yemeksepeti.repository.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerRepository  extends JpaRepository<Customer,Long> {

    Boolean existsByEmailAndPassword(String email, String password);
    Optional<Customer> findOptionalByEmailAndPassword(String email, String password);

    Boolean existsByIdAndActivationCode(Long id, String activationCode);
    Optional<Customer> findOptionalByEmailAndCardDetails(String email, String cardDetails);
}
