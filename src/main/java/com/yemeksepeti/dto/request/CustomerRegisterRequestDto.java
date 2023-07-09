package com.yemeksepeti.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRegisterRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 8,max = 64, message = "Şifre 8 ile 64 karakter arasında olmalıdır.")
    @Pattern(
            message = "Şifre enaz 8 karakter olmalı, içinde en az bir büyük bir küçük harf sayı ve rakam olmalıdır.",
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$")
    private String password;
    @NotBlank
    private String address;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String cardDetails;
}
