package co.edu.konradlorenz.strategy.impl;

import co.edu.konradlorenz.dto.request.EncryptionRequest;
import co.edu.konradlorenz.dto.response.EncryptionResponse;
import co.edu.konradlorenz.strategy.impl.concrete.PrivateCipher;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class AESEncryption extends PrivateCipher {

    private static SecretKeySpec secretKey;
    private static byte[] key;
    private Cipher cipher;

    public AESEncryption() {
        try {
            setKey();
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } catch (Exception ex) {
            System.out.println(String.format("error : %s", ex));
        }

    }

    private static void setKey() {
        MessageDigest sha = null;
        try {
            key = "example".getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            System.out.println(String.format("error : %s", e));
        }
    }

    @Override
    public EncryptionResponse encrypt(EncryptionRequest request) {
        try {

            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            String encryptedVal = Base64.getEncoder().encodeToString(cipher.doFinal(request.getValue().getBytes(StandardCharsets.UTF_8)));
            return new EncryptionResponse(request.getEncryptType(), encryptedVal);
        } catch (Exception e) {
            System.out.println(String.format("error : %s", e));
            return null;
        }
    }

    @Override
    public EncryptionResponse decrypt(EncryptionRequest request) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            String decrypted = new String(cipher.doFinal(Base64.getDecoder().decode(request.getValue())));

            return new EncryptionResponse(request.getEncryptType(), decrypted);
        } catch (Exception e) {
            System.out.println(String.format("error : %s", e));
            return null;
        }

    }
}
