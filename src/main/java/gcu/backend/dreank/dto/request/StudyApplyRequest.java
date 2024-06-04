package gcu.backend.dreank.dto.request;

import gcu.backend.dreank.domain.study.enums.Verify;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class StudyApplyRequest {
    private Verify verify;
}
