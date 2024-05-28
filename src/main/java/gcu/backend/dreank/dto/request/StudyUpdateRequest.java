package gcu.backend.dreank.dto.request;

import gcu.backend.dreank.domain.study.Study;
import gcu.backend.dreank.domain.study.enums.StudyStatus;
import lombok.Data;

@Data
public class StudyUpdateRequest {
    private Long studyId;
    private Long userId;
    private StudyStatus verify;

    public StudyStatus getVerify() {
        return verify;
    }
}
