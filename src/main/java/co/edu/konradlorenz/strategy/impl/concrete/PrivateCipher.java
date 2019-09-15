package co.edu.konradlorenz.strategy.impl.concrete;

import co.edu.konradlorenz.dto.request.EncryptionRequest;
import co.edu.konradlorenz.dto.response.EncryptionResponse;
import co.edu.konradlorenz.strategy.EncryptionStrategy;

public abstract class PrivateCipher implements EncryptionStrategy {
    @Override
    public abstract EncryptionResponse encrypt(EncryptionRequest request);

    @Override
    public abstract EncryptionResponse decrypt(EncryptionRequest request);
}
