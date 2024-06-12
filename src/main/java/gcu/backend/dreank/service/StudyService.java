package gcu.backend.dreank.service;

import gcu.backend.dreank.domain.mapping.UserStudy;
import gcu.backend.dreank.domain.study.Study;
import gcu.backend.dreank.domain.study.enums.StudyStatus;
import gcu.backend.dreank.domain.study.enums.Verify;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

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

    public List<Study> findByTag(String tag) {
        List<Study> studyList = studyRepository.findByTag(tag);
        if(studyList.isEmpty()) {
            throw new IllegalArgumentException("해당 태그의 스터디가 없습니다. tag=" + tag);
        }

        return studyList;
    }

    public HashSet<Study> findMyStudy(Long userid) {
        User user = userRepository.findById(userid)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 사용자가 없습니다."));

        List<UserStudy> userStudyList = userStudyRepository.findByUser(user);
        HashSet<Study> studySet = new HashSet<>();

        for(UserStudy userStudy : userStudyList) {
            if (userStudy.getVerify() == Verify.ACCEPT) {
                studySet.add(userStudy.getStudy());
            }
        }


        return studySet;
    }

    public List<Study> findMakeStudy(Long userid) {
        User user = userRepository.findById(userid)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 사용자가 없습니다."));

        // 리더 아이디로 스터디 리스트를 가져옵니다.
        return studyRepository.findAllByLeader(userid);
    }

    public HashSet<Study> findWatingStudy(Long userid) {
        User user = userRepository.findById(userid)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 사용자가 없습니다."));

        List<UserStudy> userStudyList = userStudyRepository.findByUser(user);
        HashSet<Study> studySet = new HashSet<>();

        for(UserStudy userStudy : userStudyList) {
            if (userStudy.getVerify() == Verify.WAITING) {
                studySet.add(userStudy.getStudy());
            }
        }


        return studySet;
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



    @ResponseStatus(HttpStatus.NO_CONTENT)
    public class NoContentException extends RuntimeException {
        public NoContentException(String message) {
            super(message);
        }
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

    public StudyResponse apply(Long studyid, Long userid) {
        Study study = studyRepository.findById(studyid)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 스터디가 없습니다."));

        User user = userRepository.findById(userid)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾지 못했습니다."));

        UserStudy userStudy = new UserStudy(user, study);
        userStudyRepository.save(userStudy);

        return new StudyResponse(study);
    }

    public StudyResponse accept(Long studyid, Long userid) {
        Study study = studyRepository.findById(studyid)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 스터디가 없습니다."));

        User user = userRepository.findById(userid)
                .orElseThrow(() -> new IllegalArgumentException("승인할 유저를 찾지 못했습니다."));

        UserStudy userStudy = userStudyRepository.findByUserAndStudy(user, study)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 유저스터디 매핑이 없습니다."));

        userStudy.setVerify(Verify.ACCEPT);

        userStudyRepository.save(userStudy);

        return new StudyResponse(study);
    }

    public StudyResponse completeStatus(Long studyid) {
        Study study = studyRepository.findById(studyid)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 스터디가 없습니다."));

        study.setStatus(StudyStatus.COMPLETED);
        studyRepository.save(study);

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