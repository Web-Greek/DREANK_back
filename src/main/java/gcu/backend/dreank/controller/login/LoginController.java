package gcu.backend.dreank.controller.login;

import gcu.backend.dreank.domain.user.User;
import gcu.backend.dreank.dto.request.login.LoginForm;
import gcu.backend.dreank.dto.request.login.LoginResponse;
import gcu.backend.dreank.service.login.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginForm form, HttpServletRequest request) {
        User user = loginService.login(form, request);
        if (user != null) {
            LoginResponse response = new LoginResponse(user.getId(), user.getEmail(), user.getNickname());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request) {
        loginService.logout(request);
    }
}
