package co.edu.konradlorenz.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EncryptionResponse implements Serializable {
    static final long serialVersionUID = -6206439368333086550L;

    private String type;
    private String data;

    public EncryptionResponse() {
    }

    public EncryptionResponse(String type, String data) {
        this.type = type;
        this.data = data;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
