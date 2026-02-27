package org.kh.monopolyidentityservice.dto.register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class RegisterRequestDto {
    private String username;
    private String password;
    private String email;
}
