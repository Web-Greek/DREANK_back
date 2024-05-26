package gcu.backend.dreank.controller.login;

import gcu.backend.dreank.dto.request.login.LoginForm;
import gcu.backend.dreank.service.login.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public void login(@RequestBody LoginForm form, HttpServletRequest request){
        loginService.login(form, request);
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request){
        loginService.logout(request);
    }
}
