package gcu.backend.dreank.domain.chat;

import gcu.backend.dreank.domain.common.BaseEntity;
import gcu.backend.dreank.domain.mapping.ChatRoom;
import gcu.backend.dreank.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Message extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id") //외래키는 chat_id
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") //외래키는 user_id
    private User user;

    //@Enumerated(EnumType.STRING)
    //private MsgType type;// ENTER, EXIT, TALK

    //private String nickname;//보낸 사람
    @Column(nullable = false, length = 100)
    private String message;//메세지

    //첨부파일 경로
    private String s3DataUrl;
    private String fileName;
    private String fileDir;

}
