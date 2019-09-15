package co.edu.konradlorenz.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EncryptionRequest implements Serializable {
    static final long serialVersionUID = 7069357219560733938L;

    private String value;
    private String encryptType;
    private String operationType;

    public EncryptionRequest() {
    }

    public EncryptionRequest(String value, String encryptType, String operationType) {
        this.value = value;
        this.encryptType = encryptType;
        this.operationType = operationType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getEncryptType() {
        return encryptType;
    }

    public void setEncryptType(String encryptType) {
        this.encryptType = encryptType;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
}
