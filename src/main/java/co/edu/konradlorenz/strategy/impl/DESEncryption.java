package co.edu.konradlorenz.strategy.impl;

import co.edu.konradlorenz.dto.request.EncryptionRequest;
import co.edu.konradlorenz.dto.response.EncryptionResponse;
import co.edu.konradlorenz.strategy.impl.concrete.PrivateCipher;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;

public class DESEncryption extends PrivateCipher {

    private Cipher cipher;
    private String key;

    public DESEncryption() {
        try {
            key = "pattern1891";
            cipher = Cipher.getInstance("DES");

        } catch (Exception ex) {
            System.out.println(String.format("error : %s", ex));
        }

    }

    private SecretKey getKey() {
        try {
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
            return skf.generateSecret(dks);
        } catch (Exception ex) {
            return null;
        }

    }

    @Override
    public EncryptionResponse encrypt(EncryptionRequest request) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, getKey());
            byte[] utf8 = request.getValue().getBytes(StandardCharsets.UTF_8);
            byte[] enc = cipher.doFinal(utf8);
            String encryptedString = new sun.misc.BASE64Encoder().encode(enc);

            return new EncryptionResponse(request.getEncryptType(), encryptedString);
        } catch (Exception ex) {
            return null;
        }

    }

    @Override
    public EncryptionResponse decrypt(EncryptionRequest request) {

        try {
            cipher.init(Cipher.DECRYPT_MODE, getKey());
            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(request.getValue());
            byte[] utf8 = cipher.doFinal(dec);

            String decryptedString = new String(utf8, StandardCharsets.UTF_8);
            return new EncryptionResponse(request.getEncryptType(), decryptedString);
        } catch (Exception ex) {
            return null;
        }


    }
}
