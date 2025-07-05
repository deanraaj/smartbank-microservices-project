package com.smartbank.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// Made change in the Central code base. NAGARAJU DOLA

// WORKING WITH SAMPLE API
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private String name;
    private String email;
    private String phone;
}
