package com.nutrition.sweng.security;

import com.nutrition.sweng.Model.NotAuthorizedException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class SigningKeyGenerator {

    public static void main(String[] args) {
        try {
            KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
            FileOutputStream fos = new FileOutputStream("publicKey");
            fos.write(x509EncodedKeySpec.getEncoded());
            fos.close();

            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
            fos = new FileOutputStream("privateKey");
            fos.write(pkcs8EncodedKeySpec.getEncoded());
            fos.close();

            System.out.println("Key pair successfully generated.");
        } catch (Exception e) {
            throw new NotAuthorizedException("Generating token went wrong. Try again");
        }
    }
}
