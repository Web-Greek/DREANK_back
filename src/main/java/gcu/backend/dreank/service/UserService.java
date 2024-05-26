package gcu.backend.dreank.service;

import gcu.backend.dreank.domain.user.User;
import gcu.backend.dreank.domain.user.UserRepository;
import gcu.backend.dreank.dto.request.UserCreateRequest;
import lombok.RequiredArgsConstructor;

//@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

//    CREATE
    public void saveUser(UserCreateRequest request){
        userRepository.save(new User(request));
    }

//    READ

//    UPDATE

//    DELETE
    public void deleteUser(String nickname){
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(IllegalAccessError::new);
        userRepository.delete(user);
    }
}
