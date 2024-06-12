package gcu.backend.dreank.dto.request.study;

import gcu.backend.dreank.domain.study.Study;
import gcu.backend.dreank.domain.study.Tag;
import gcu.backend.dreank.domain.study.enums.Day;

import java.time.LocalTime;
import java.util.List;

public class StudyResponse {
    private String name;
    private String introduction;
    private int num_recruit;
    private LocalTime start_time;
    private LocalTime end_time;
    private String day; //MON, TUE, WED, THU, FRI, SAT, SUN
    private String tag; //스터디 tag 리스트

    public StudyResponse(Study study) {
        this.name = study.getName();
        this.introduction = study.getIntroduction();
        this.num_recruit = study.getNum_recruit();
        this.start_time = study.getStart_time();
        this.end_time = study.getEnd_time();
        this.day = study.getDay();
        this.tag = study.getTag();
    }
}