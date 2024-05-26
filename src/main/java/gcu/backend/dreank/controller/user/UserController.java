package gcu.backend.dreank.controller.user;

import gcu.backend.dreank.service.user.UserService;
import gcu.backend.dreank.dto.request.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
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

//    UPDATE- 정보 수정
    //@PutMapping
    //public void updateUser(@RequestBody UserUpdateRequest request){

    //}

//    DELETE
//    탈퇴하기
    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String nickname){

    }
}
