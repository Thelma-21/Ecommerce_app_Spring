package com.thelma.ecommercewebsite.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDto {
    private String fullName;
    private String email;
    private String password;
}
