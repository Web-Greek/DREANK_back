package gcu.backend.dreank.domain.mapping;

import gcu.backend.dreank.domain.common.BaseEntity;
import gcu.backend.dreank.domain.study.Study;
import gcu.backend.dreank.domain.study.enums.Verify;
import gcu.backend.dreank.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserChat extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //  user를 참조하는 N:1관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") //외래키는 user_id
    private User user; //user 객체

    //  user를 참조하는 N:1관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id") //외래키는 chat_id
    private ChatRoom chatRoom;
}