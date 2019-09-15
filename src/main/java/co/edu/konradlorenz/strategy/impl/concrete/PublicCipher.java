package co.edu.konradlorenz.strategy.impl.concrete;

import co.edu.konradlorenz.dto.request.EncryptionRequest;
import co.edu.konradlorenz.dto.response.EncryptionResponse;
import co.edu.konradlorenz.strategy.EncryptionStrategy;

public abstract class PublicCipher implements EncryptionStrategy {
    @Override
    public abstract EncryptionResponse encrypt(EncryptionRequest request) throws Exception;

    @Override
    public abstract EncryptionResponse decrypt(EncryptionRequest request) throws Exception;
}
