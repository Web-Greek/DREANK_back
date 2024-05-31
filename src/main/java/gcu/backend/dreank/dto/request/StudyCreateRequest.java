package gcu.backend.dreank.dto.request;

import gcu.backend.dreank.domain.study.Study;
import gcu.backend.dreank.domain.study.Tag;
import gcu.backend.dreank.domain.study.enums.Day;
import gcu.backend.dreank.domain.study.enums.StudyStatus;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class StudyCreateRequest {

    private String name;
    private String introduction;
    private int num_recruit;
    private LocalTime start_time;
    private LocalTime end_time;
    private Day day; //MON, TUE, WED, THU, FRI, SAT, SUN
    private List<Tag> tagList; //스터디 tag 리스트

    public Study toEntity() {
        return Study.builder()
                .name(this.name)
                .introduction(this.introduction)
                .num_recruit(this.num_recruit)
                .start_time(this.start_time)
                .end_time(this.end_time)
                .day(this.day)
                .tagList(this.tagList)
                .status(StudyStatus.RECRUIT)
                .build();
    }
}