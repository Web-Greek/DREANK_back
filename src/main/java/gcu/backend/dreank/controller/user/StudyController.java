package gcu.backend.dreank.controller.user;

import gcu.backend.dreank.domain.study.Study;
import gcu.backend.dreank.domain.study.enums.StudyStatus;
import gcu.backend.dreank.dto.request.study.StudyCreateRequest;
import gcu.backend.dreank.dto.request.study.StudyCreateResponse;
import gcu.backend.dreank.dto.request.study.StudyResponse;
import gcu.backend.dreank.dto.request.study.StudyUpdateRequest;
import gcu.backend.dreank.dto.request.login.SessionConst;
import gcu.backend.dreank.dto.request.login.SessionInfo;
import gcu.backend.dreank.service.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/study")
@Slf4j
public class StudyController {
    private final StudyService studyService;

    //Create
    //스터디 생성
    @PostMapping("/post")
    @ResponseBody
    public StudyCreateResponse createStudy(@RequestBody StudyCreateRequest request) {
        return studyService.save(request.toEntity());
    }

    //Read
    //스터디 전체 조회
    @GetMapping(value = "/search")
    @ResponseBody
    public List<Study> getStudy() {
        return studyService.find();
    }

    //스터디 id 통해 모임 찾기
    @GetMapping(value = "/search/{id}")
    //.... continue
    @ResponseBody
    public StudyResponse getStudy(@PathVariable("id") Long id) {
        return studyService.findById(id);
    }

    //스터디 name 통해 모임 찾기
    @GetMapping(value = "/search/{name}")
    @ResponseBody
    public List<StudyResponse> getStudy(@PathVariable("name") String name) {
        return studyService.findByName(name);
    }

    //스터디 랭킹 조회
    @GetMapping(value = "/search/rank")
    @ResponseBody
    //response에서 필요한 정보만 따오기
    public List<StudyResponse> getStudyByRank() {
        return studyService.findThreeRank();
    }

    //모집중,마감여부 필터링
    @GetMapping(value = "/{status}")
    @ResponseBody
    public List<StudyResponse> getStudyByStatus(@PathVariable("status") StudyStatus status) {
        return studyService.findByStatus(status);
    }

    //Update
    //구성원 승인 - joinstudy 수정 필요
    @PatchMapping("/accept/{studyid}")
    @ResponseBody
    public StudyResponse update(@PathVariable Long studyid, @SessionAttribute(SessionConst.LOGIN_MEMBER) SessionInfo sessionInfo, @RequestBody StudyUpdateRequest request) {
        request.setStudyId(studyid); //request할 study id를 설정
        request.setUserId(sessionInfo.getId());
        return studyService.accept(request);
    }

    //delete
    //@DeleteMapping("/{id}")
    //public void delete(@PathVariable Long id) {
     //   return studyService.delete();
    //}


}
