package co.edu.konradlorenz.strategy.impl;

import co.edu.konradlorenz.dto.request.EncryptionRequest;
import co.edu.konradlorenz.dto.response.EncryptionResponse;
import co.edu.konradlorenz.strategy.impl.concrete.PublicCipher;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class RSAEncryption extends PublicCipher {

    private PrivateKey privateKey;
    private PublicKey publicKey;
    private Cipher cipher;

    private String privateKeyString;


    public RSAEncryption() {
        try {
            cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(1024);
            KeyPair pair = keyGen.generateKeyPair();
            this.privateKey = pair.getPrivate();
            this.publicKey = pair.getPublic();
        } catch (Exception ex) {
            System.out.println(String.format("error :  %s", ex));
        }
        this.privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());

    }

    public PrivateKey getPrivateKey () {
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyString.getBytes()));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return privateKey;
    }


    @Override
    public EncryptionResponse encrypt(EncryptionRequest request) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedData = cipher.doFinal(request.getValue().getBytes());
            byte [] encryptedCast = Base64.getEncoder().encode(encryptedData);
            return new EncryptionResponse(request.getEncryptType(), new String(encryptedCast));
        } catch (Exception ex) {
            System.out.println(String.format("error :  %s", ex));
            return null;
        }


    }

    @Override
    public EncryptionResponse decrypt(EncryptionRequest request) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, getPrivateKey());
            byte [] decodedData = Base64.getDecoder().decode(request.getValue().getBytes());
            byte[] decrypted = cipher.doFinal(decodedData);
            return new EncryptionResponse(request.getEncryptType(), new String(decrypted));
        } catch (Exception ex) {
            System.out.println(String.format("error :  %s", ex));
            return null;
        }
    }
}
