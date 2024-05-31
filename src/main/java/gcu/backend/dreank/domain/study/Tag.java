package gcu.backend.dreank.domain.study;

import gcu.backend.dreank.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Tag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //  study를 참조하는 N:1관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id") //외래키는 study_id
    private Study study; //user 객체

    @Column(nullable = false, length = 20)
    private String content;
}