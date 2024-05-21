package gcu.backend.dreank.domain.chat;

import gcu.backend.dreank.domain.user.User;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
public class ChatRoom {
//    채팅방 id
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

//    @OneToOne
    @JoinColumn(name = "study_id")
    private Long study_id;

    private String title;
}
