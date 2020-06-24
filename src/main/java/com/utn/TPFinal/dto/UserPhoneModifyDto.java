package com.utn.TPFinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPhoneModifyDto {
    Integer user;
    String name;
    String lastName;
    String dni;
    String password;
    Integer city;
    String lineType;
}
