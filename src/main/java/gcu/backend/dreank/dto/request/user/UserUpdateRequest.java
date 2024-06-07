package gcu.backend.dreank.dto.request.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class UserUpdateRequest {
    private String nickname;
    private String password;
    private String new_password;
    private String introduce; // 한줄 소개

    // 객체를 JSON 문자열로 변환하는 메서드
    public String toJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Fail to convert object to JSON string", e);
        }
    }
}