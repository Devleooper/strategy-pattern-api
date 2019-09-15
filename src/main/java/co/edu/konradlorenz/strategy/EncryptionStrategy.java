package co.edu.konradlorenz.strategy;

import co.edu.konradlorenz.dto.request.EncryptionRequest;
import co.edu.konradlorenz.dto.response.EncryptionResponse;

public interface EncryptionStrategy {

    EncryptionResponse encrypt(EncryptionRequest request) throws Exception;
    EncryptionResponse decrypt(EncryptionRequest request) throws Exception;
}
