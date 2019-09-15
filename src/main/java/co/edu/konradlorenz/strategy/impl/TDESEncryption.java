package co.edu.konradlorenz.strategy.impl;

import co.edu.konradlorenz.dto.request.EncryptionRequest;
import co.edu.konradlorenz.dto.response.EncryptionResponse;
import co.edu.konradlorenz.strategy.impl.concrete.PrivateCipher;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

public class TDESEncryption extends PrivateCipher {

    private MessageDigest md;

    public TDESEncryption() {
        try {
            md = MessageDigest.getInstance("md5");
        } catch (Exception ex) {
            System.out.println(String.format("error :  %s", ex));
        }

    }

    public byte[] getKeyBytes()  {
        byte[] arr = md.digest("ABCDEABCDE"
                .getBytes(StandardCharsets.UTF_8));

        return Arrays.copyOf(arr, 24);
    }

    @Override
    public EncryptionResponse encrypt(EncryptionRequest request) {
        try {
            byte[] keyBytes = getKeyBytes();
            for (int a = 0, k = 16; a < 8; ) {
                keyBytes[k++] = keyBytes[a++];
            }

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            IvParameterSpec iv = new IvParameterSpec(new byte[8]);
            Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);

            byte[] plainTextBytes = request.getValue().getBytes(StandardCharsets.UTF_8);
            byte[] cipherText = cipher.doFinal(plainTextBytes);

            return new EncryptionResponse(request.getEncryptType(), Base64.encodeBase64String(cipherText));
        } catch (Exception ex) {
            return null;
        }

    }

    @Override
    public EncryptionResponse decrypt(EncryptionRequest request) {

        try {

            byte[] keyBytes = getKeyBytes();
            for (int j = 0, k = 16; j < 8; ) {
                keyBytes[k++] = keyBytes[j++];
            }

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            IvParameterSpec iv = new IvParameterSpec(new byte[8]);
            Cipher decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            decipher.init(Cipher.DECRYPT_MODE, key, iv);

            byte[] plainText = decipher.doFinal( Base64.decodeBase64(request.getValue()));

            return new EncryptionResponse(request.getEncryptType(), new String(plainText, StandardCharsets.UTF_8));
        } catch (Exception ex) {
            return null;
        }

    }
}
