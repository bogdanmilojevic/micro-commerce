package com.microcommerce.productservice.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class RSAPublicKeyProvider {

    private final String publicKeyPEM;
    private final RSAPublicKey publicKey;

    public RSAPublicKeyProvider(@Value("${jwt.public-key}") String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        this.publicKeyPEM = publicKey;
        this.publicKey = constructPublicKey();
    }

    private RSAPublicKey constructPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyPEM);
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(publicKeySpec);
    }

    public RSAPublicKey getPublicKey() {
        return publicKey;
    }
}