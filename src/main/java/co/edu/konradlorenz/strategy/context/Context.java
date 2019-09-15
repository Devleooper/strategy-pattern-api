package co.edu.konradlorenz.strategy.context;

import co.edu.konradlorenz.dto.request.EncryptionRequest;
import co.edu.konradlorenz.dto.response.EncryptionResponse;
import co.edu.konradlorenz.strategy.EncryptionStrategy;

public class Context {

    private EncryptionStrategy currentStrategy;

    public Context(EncryptionStrategy currentStrategy) {
        this.currentStrategy = currentStrategy;
    }

    public Context() {
    }

    public EncryptionStrategy getCurrentStrategy() {
        return currentStrategy;
    }

    public void setCurrentStrategy(EncryptionStrategy currentStrategy) {
        this.currentStrategy = currentStrategy;
    }

    public EncryptionResponse doEncryption(EncryptionRequest encryptionRequest) throws Exception {
        return currentStrategy.encrypt(encryptionRequest);
    }

    public EncryptionResponse doDecryption(EncryptionRequest encryptionRequest) throws Exception {
        return currentStrategy.decrypt(encryptionRequest);
    }
}
