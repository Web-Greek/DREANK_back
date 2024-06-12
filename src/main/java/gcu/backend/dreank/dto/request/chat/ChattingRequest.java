package gcu.backend.dreank.dto.request.chat;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChattingRequest {
    private Long userId;

    private String timestamp;

    private String content;//메세지
}
