package gcu.backend.dreank.dto.request;

import gcu.backend.dreank.domain.study.Study;
import gcu.backend.dreank.domain.user.User;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StudyCreateResponse {
    private Long id;
    private String name;

    public static StudyCreateResponse of(final Study study) {
        return new StudyCreateResponse(study.getId(), study.getName());
        //return new StudyCreateResponse(study)
    }
}
