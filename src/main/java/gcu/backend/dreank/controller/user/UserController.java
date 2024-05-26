package gcu.backend.dreank.controller.user;

import gcu.backend.dreank.service.UserService;
import gcu.backend.dreank.dto.request.user.UserCreateRequest;
import gcu.backend.dreank.dto.request.user.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

//    CREATE- 회원가입
    @PostMapping
    public void saveUser(@RequestBody UserCreateRequest request){
        userService.saveUser(request);
    }

//    UPDATE- 정보 수정
    //@PutMapping
    //public void updateUser(@RequestBody UserUpdateRequest request){

    //}

//    DELETE
//    탈퇴하기
    @DeleteMapping
    public void deleteUser(@RequestParam String nickname){

    }
}
