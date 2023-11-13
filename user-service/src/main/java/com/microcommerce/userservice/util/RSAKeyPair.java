package com.microcommerce.userservice.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
@Getter
public class RSAKeyPair {

    private final RSAPublicKey publicKey;
    private final RSAPrivateKey privateKey;

    private final String publicKeyPEM;
    private final String privateKeyPEM;

    public RSAKeyPair(@Value("${jwt.public-key}") String publicKey, @Value("${jwt.private-key}") String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        this.publicKeyPEM = publicKey;
        this.privateKeyPEM = privateKey;
        this.publicKey = generatePublicKey();
        this.privateKey = generatePrivateKey();
    }

    private RSAPublicKey generatePublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyPEM);
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(publicKeySpec);
    }

    private RSAPrivateKey generatePrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyPEM);
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        return (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(privateKeySpec);
    }
}
