package com.yemeksepeti.repository.entity;

import com.yemeksepeti.repository.enums.EStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="tblcustomer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String address;
    private String phoneNumber;
    private String password;
    private String cardDetails;
    private String balance;
    private String activationCode;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EStatus status = EStatus.PENDING;


}
