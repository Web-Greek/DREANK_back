package gcu.backend.dreank.service.user;

import gcu.backend.dreank.domain.user.User;
import gcu.backend.dreank.repository.UserRepository;
import gcu.backend.dreank.dto.request.user.UserCreateRequest;
import gcu.backend.dreank.dto.request.user.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

//    CREATE
    @Transactional
    public void saveUser(UserCreateRequest request){
        userRepository.save(new User(request));
    }

//    READ
    @Transactional(readOnly = true)
    public boolean chkPwd(UserUpdateRequest request, Long id){
        User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return user.getPassword().equals(request.getPassword());
    }

//    UPDATE
    public void updateNickname(User user, String nickname){
//        닉네임 중복 확인
        if(userRepository.existsByNickname(nickname))
            throw new IllegalArgumentException("이미 존재하는 닉네임 입니다.");
        user.setNickname(nickname);
    }

    @Transactional
    public void updateUser(UserUpdateRequest request, Long id){
        User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
//        닉네임 변경
        if(request.getNickname() != null)
            updateNickname(user, request.getNickname());
//        비밀번호 변경
        else if(request.getNew_password() != null){
            user.setPassword(request.getNew_password());
        }
    }

//    DELETE
    @Transactional
    public void deleteUser(long id){
        User user = userRepository.findById(id)
                .orElseThrow(IllegalAccessError::new);
        userRepository.delete(user);
    }
}
