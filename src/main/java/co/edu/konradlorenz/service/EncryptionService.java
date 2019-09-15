package co.edu.konradlorenz.service;

import co.edu.konradlorenz.dto.request.EncryptionRequest;
import co.edu.konradlorenz.dto.response.EncryptionResponse;
import co.edu.konradlorenz.strategy.EncryptionStrategy;
import co.edu.konradlorenz.strategy.context.Context;
import co.edu.konradlorenz.strategy.impl.*;

import java.util.HashMap;
import java.util.Map;

import static org.glassfish.jersey.internal.guava.Preconditions.checkNotNull;

public class EncryptionService {
    /**
     * Encryption/Decryption types
     */
    private static final String ENCRYPT_DES_TYPE = "DES";
    private static final String ENCRYPT_3DES_TYPE = "3DES";
    private static final String ENCRYPT_AES_TYPE = "AES";
    private static final String ENCRYPT_RSA_TYPE = "RSA";
    private static final String ENCRYPT_ELGAMAL_TYPE = "ElGamal";
    /**
     * Operation types encrypt/decrypt
     */
    private static final String ENCRYPT_OPERATION_TYPE = "enc";
    private static final String DECRYPT_OPERATION_TYPE = "dec";

    private Context context;
    private Map<String, EncryptionStrategy> strategies;

    public EncryptionService() {
        this.context = new Context();
        this.strategies = new HashMap<>();
        addStrategies();
    }


    public EncryptionResponse processAction(EncryptionRequest encryptionRequest) throws Exception {
        checkNotNull(encryptionRequest);

        switch (encryptionRequest.getEncryptType()) {

            case ENCRYPT_DES_TYPE:
                context.setCurrentStrategy(strategies.get(ENCRYPT_DES_TYPE));
                break;
            case ENCRYPT_3DES_TYPE:
                context.setCurrentStrategy(strategies.get(ENCRYPT_3DES_TYPE));
                break;
            case ENCRYPT_AES_TYPE:
                context.setCurrentStrategy(strategies.get(ENCRYPT_AES_TYPE));
                break;
            case ENCRYPT_RSA_TYPE:
                context.setCurrentStrategy(strategies.get(ENCRYPT_RSA_TYPE));
                break;
            case ENCRYPT_ELGAMAL_TYPE:
                context.setCurrentStrategy(strategies.get(ENCRYPT_ELGAMAL_TYPE));
                break;
            default:
                return null;
        }


        if (ENCRYPT_OPERATION_TYPE.equals(encryptionRequest.getOperationType()))
            return context.doEncryption(encryptionRequest);
        else if (DECRYPT_OPERATION_TYPE.equals(encryptionRequest.getOperationType()))
            return context.doDecryption(encryptionRequest);
        else
            return null;


    }

    private void addStrategies() {
        strategies.put(ENCRYPT_DES_TYPE, new DESEncryption());
        strategies.put(ENCRYPT_3DES_TYPE, new TDESEncryption());
        strategies.put(ENCRYPT_AES_TYPE, new AESEncryption());
        strategies.put(ENCRYPT_RSA_TYPE, new RSAEncryption());
        strategies.put(ENCRYPT_ELGAMAL_TYPE, new ElGamalEncryption());
    }
}
