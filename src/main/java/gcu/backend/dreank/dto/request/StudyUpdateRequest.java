package gcu.backend.dreank.dto.request;

import gcu.backend.dreank.domain.study.Study;
import gcu.backend.dreank.domain.study.enums.StudyStatus;
import gcu.backend.dreank.domain.study.enums.Verify;
import lombok.Data;

@Data
public class StudyUpdateRequest {
    private Long studyId;
    private Long userId;
    private Verify verify;

    public Verify getVerify() {
        return verify;
    }
}
