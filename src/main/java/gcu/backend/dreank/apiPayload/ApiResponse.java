package gcu.backend.dreank.apiPayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse {
    @JsonProperty("isSuccess")
    private final boolean isSuccess;
    private final String code;
    private final String message;
    //미완성
    //@JsonIn
}
