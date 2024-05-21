package gcu.backend.dreank.domain.chat;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
public class Message {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "study_id")
    private Long studyId;//채팅방 ID

    @Enumerated(EnumType.STRING)
    private MsgType type;// ENTER, EXIT, TALK

    private String nickname;//보낸 사람

    private String message;//메세지

    private LocalDateTime time;//채팅 시간

//    첨부파일 경로
    private String s3DataUrl;
    private String fileName;
    private String fileDir;

}
