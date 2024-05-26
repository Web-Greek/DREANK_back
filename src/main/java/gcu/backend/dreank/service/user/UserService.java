package gcu.backend.dreank.service.user;

import gcu.backend.dreank.domain.user.User;
import gcu.backend.dreank.repository.UserRepository;
import gcu.backend.dreank.dto.request.user.UserCreateRequest;
import gcu.backend.dreank.dto.request.user.UserUpdateRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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


//    UPDATE
    public void updateNickname(User user, String nickname){
//        닉네임 중복 확인
        if(userRepository.existsByNickname(nickname))
            throw new IllegalArgumentException();
        user.setNickname(nickname);
        userRepository.save(user);
    }

    public void updatePassword(User user, String password, String new_password){
//        check password
        if(!user.getPassword().equals(password)){
            throw new IllegalArgumentException();
        }else{
            user.setPassword(new_password);
            userRepository.save(user);
        }
    }

    public void updateEmail(User user, String email){
        user.setEmail(email);
        userRepository.save(user);
    }

    public void updateUser(UserUpdateRequest request, Long id){
        User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        if(request.getNickname() != null)
            updateNickname(user, request.getNickname());
        else if(request.getPassword() != null)
            updatePassword(user, request.getPassword(), request.getNew_password());
        else if(request.getEmail() != null)
            updateEmail(user, request.getEmail());
    }

//    DELETE
    public void deleteUser(String nickname){
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(IllegalAccessError::new);
        userRepository.delete(user);
    }
}
