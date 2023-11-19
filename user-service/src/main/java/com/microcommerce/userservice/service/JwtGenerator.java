package com.microcommerce.userservice.service;

import com.microcommerce.userservice.util.RSAKeyPair;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtGenerator {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final RSAKeyPair rsaKeyPair;

    /**
     * @param authentication
     * @return
     */
    public String generateJwt(Authentication authentication, String userId) {
        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .subject(authentication.getName())
                .claim("user_id", userId)
                .claim("roles", roles)
                .issuedAt(Instant.now())
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
