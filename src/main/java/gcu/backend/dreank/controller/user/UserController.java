package gcu.backend.dreank.controller.user;

import gcu.backend.dreank.domain.user.User;
import gcu.backend.dreank.dto.request.login.SessionConst;
import gcu.backend.dreank.dto.request.login.SessionInfo;
import gcu.backend.dreank.dto.request.user.UserCreateRequest;
import gcu.backend.dreank.dto.request.user.UserUpdateRequest;
import gcu.backend.dreank.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // CREATE- 회원가입
    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody UserCreateRequest request) {
        userService.saveUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    // 비밀번호 check - 마이페이지 진입
    @PostMapping("/{userId}/mypage")
    public ResponseEntity<String> checkPwd(@PathVariable Long userId, @RequestBody UserUpdateRequest request) {
        boolean isPasswordCorrect = userService.chkPwd(request, userId);
        if (isPasswordCorrect) {
            return ResponseEntity.ok("Password is correct");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
        }
    }

    // UPDATE- 정보 수정
    @PatchMapping("/{userId}/update")
    public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody UserUpdateRequest request) {
        userService.updateUser(request, userId);
        return ResponseEntity.ok("User updated successfully");
    }

    // DELETE - 탈퇴하기
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

    // GET - 사용자 정보 가져오기
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }
}
