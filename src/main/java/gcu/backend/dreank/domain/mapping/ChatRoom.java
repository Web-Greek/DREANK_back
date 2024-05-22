package gcu.backend.dreank.domain.mapping;

import gcu.backend.dreank.domain.common.BaseEntity;
import gcu.backend.dreank.domain.study.Study;
import gcu.backend.dreank.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChatRoom extends BaseEntity {
//  채팅방 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private List<UserChat> userChatList = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id") //외래키는 study_id
    private Study study;
}
