package gcu.backend.dreank.controller.user;

import gcu.backend.dreank.dto.request.login.SessionConst;
import gcu.backend.dreank.dto.request.login.SessionInfo;
import gcu.backend.dreank.dto.request.user.UserCreateRequest;
import gcu.backend.dreank.dto.request.user.UserUpdateRequest;
import gcu.backend.dreank.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

//    CREATE- 회원가입
    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request){
        userService.saveUser(request);
    }

//  비밃번호 check - 마이페이지 진입
    @PostMapping("/user/mypage")
    public boolean checkPwd(@RequestBody UserUpdateRequest request,
                         @SessionAttribute(SessionConst.LOGIN_MEMBER) SessionInfo sessionInfo){
        return userService.chkPwd(request, sessionInfo.getId());
    }
//    UPDATE- 정보 수정
    @PatchMapping("/user/update")
    public void updateUserNickname(
            @RequestBody UserUpdateRequest request, @SessionAttribute(SessionConst.LOGIN_MEMBER) SessionInfo sessionInfo){
        userService.updateUser(request, sessionInfo.getId());
    }

    @PatchMapping("/user/update")
    public void updateUserPwd(
            @RequestBody UserUpdateRequest request, @SessionAttribute(SessionConst.LOGIN_MEMBER) SessionInfo sessionInfo){
        userService.updateUser(request, sessionInfo.getId());
    }

//    DELETE
//    탈퇴하기
    @DeleteMapping("/user")
    public void deleteUser(@SessionAttribute(SessionConst.LOGIN_MEMBER) SessionInfo sessionInfo){
        userService.deleteUser(sessionInfo.getId());
    }
}
