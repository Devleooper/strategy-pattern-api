package co.edu.konradlorenz.strategy.impl;

import co.edu.konradlorenz.dto.request.EncryptionRequest;
import co.edu.konradlorenz.dto.response.EncryptionResponse;
import co.edu.konradlorenz.strategy.impl.concrete.PublicCipher;

import java.security.KeyPair;
import java.util.HashMap;


public class ElGamalEncryption extends PublicCipher {

    private HashMap<String, KeyPair> pairs = new HashMap<>();


    @Override
    public EncryptionResponse encrypt(EncryptionRequest request) throws Exception {
        return new EncryptionResponse(request.getEncryptType(), "not supported yet.");
    }

    @Override
    public EncryptionResponse decrypt(EncryptionRequest request) throws Exception {
        return new EncryptionResponse(request.getEncryptType(), "no supported yet.");

    }
}
