package gcu.backend.dreank.dto.request.study;

import gcu.backend.dreank.domain.study.enums.Verify;
import lombok.Data;

@Data
public class StudyUpdateRequest {
    private Long studyId;
    private Long userId;
    private Verify verify;
}
