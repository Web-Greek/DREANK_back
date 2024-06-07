package gcu.backend.dreank.service.login;

import gcu.backend.dreank.dto.request.login.SessionConst;
import gcu.backend.dreank.dto.request.login.SessionInfo;
import gcu.backend.dreank.domain.user.User;
import gcu.backend.dreank.repository.UserRepository;
import gcu.backend.dreank.dto.request.login.LoginForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User login(LoginForm form, HttpServletRequest request){
        User user = userRepository.findByEmailAndPassword(form.getEmail(), form.getPassword())
                .orElseThrow(IllegalAccessError::new);

//      로그인 성공 시 세션 생성
        HttpSession session = request.getSession();
//      세션에 정보 올려두기 - 닉네임
        SessionInfo userSession = new SessionInfo();
        userSession.setId(user.getId());
        userSession.setNickname(user.getNickname());

        session.setAttribute(SessionConst.LOGIN_MEMBER, userSession);
        return user;
    }

    public void logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        System.out.println("logout");
    }
}
