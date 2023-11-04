package net.blog.springbootrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTAuthResponseDto {
    private String accessToken;
    private String tokenType ="Bearer";
}
