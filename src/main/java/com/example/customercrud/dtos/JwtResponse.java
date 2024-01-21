

package com.example.customercrud.dtos;

import com.example.customercrud.model.Login;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtResponse {

    private String jwtToken;

    private LoginDto login;
}


