package gcu.backend.dreank.service;

import gcu.backend.dreank.domain.mapping.UserStudy;
import gcu.backend.dreank.domain.study.Study;
import gcu.backend.dreank.domain.study.enums.StudyStatus;
import gcu.backend.dreank.domain.user.User;
import gcu.backend.dreank.dto.request.study.StudyCreateResponse;
import gcu.backend.dreank.dto.request.study.StudyResponse;
import gcu.backend.dreank.dto.request.study.StudyUpdateRequest;
import gcu.backend.dreank.repository.StudyRepository;
import gcu.backend.dreank.repository.UserRepository;
import gcu.backend.dreank.repository.UserStudyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class StudyService {
    private final StudyRepository studyRepository;
    private final UserRepository userRepository;
    private final UserStudyRepository userStudyRepository;

    public StudyCreateResponse save(final Study study) {
        //유효성 검사 하려면 여기서
        return StudyCreateResponse.of(studyRepository.save(study));
    }

    public List<Study> find() {
        return studyRepository.findAll();
    }

    public StudyResponse findById(Long id) {
        Study study = studyRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new StudyResponse(study);
    }

    public List<StudyResponse> findByTag(String tag) {
        List<Study> studyList = studyRepository.findByTag(tag);
        List<StudyResponse> studyResponseList = new ArrayList<>();
        if(studyList.isEmpty()) {
            throw new IllegalArgumentException("해당 태그의 스터디가 없습니다. tag=" + tag);
        }

        return studyResponseList;
    }

    public List<StudyResponse> findByName(String name) {
        List<Study> studyList = studyRepository.findByName(name);
        List<StudyResponse> studyResponseList = new ArrayList<>();
        if(studyList.isEmpty()) {
            throw new IllegalArgumentException("해당 이름의 스터디가 없습니다. name=" + name);
        }

        for(Study study : studyList) {
            studyResponseList.add(new StudyResponse(study));
        }
        return studyResponseList;
    }

    public List<StudyResponse> findThreeRank() {
        List<Study> studyList = studyRepository.findTop3ByOrderByScoreDesc();
        List<StudyResponse> studyResponseList = new ArrayList<>();

        if(studyList.isEmpty()) {
            throw new IllegalArgumentException("해당하는 스터디가 없습니다.");
        }

        for(Study study : studyList) {
            studyResponseList.add(new StudyResponse(study));
        }
        return studyResponseList;
    }

    public List<StudyResponse> findByStatus(StudyStatus status) {
        List<Study> studyList = studyRepository.findByStatus(status);
        List<StudyResponse> studyResponseList = new ArrayList<>();

        if(studyList.isEmpty()) {
            throw new IllegalArgumentException("해당하는 스터디가 없습니다.");
        }

        for(Study study : studyList) {
            studyResponseList.add(new StudyResponse(study));
        }
        return studyResponseList;
    }

    public StudyResponse accept(StudyUpdateRequest request) {
        Study study = studyRepository.findById(request.getStudyId())
                .orElseThrow(() -> new IllegalArgumentException("해당하는 스터디가 없습니다."));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("승인할 유저를 찾지 못했습니다."));

        UserStudy userStudy = userStudyRepository.findByUserAndStudy(user, study)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 유저스터디 매핑이 없습니다."));

        userStudy.setVerify(request.getVerify());

        userStudyRepository.save(userStudy);

        return new StudyResponse(study);
    }

    public void deleteStudy(long id){
        Study study = studyRepository.findById(id)
                .orElseThrow(IllegalAccessError::new);
        studyRepository.delete(study);
    }
}

/*
public Study saveStudy(@RequestBody StudyCreateRequest request) {
        return studyService.create(request);
    }
 */