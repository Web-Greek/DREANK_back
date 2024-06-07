package gcu.backend.dreank.service.user;

import gcu.backend.dreank.domain.user.User;
import gcu.backend.dreank.dto.request.StudyCreateResponse;
import gcu.backend.dreank.dto.request.user.UserCreateResponse;
import gcu.backend.dreank.repository.CalendarRepository;
import gcu.backend.dreank.repository.StudyRepository;
import gcu.backend.dreank.repository.UserRepository;
import gcu.backend.dreank.dto.request.user.UserCreateRequest;
import gcu.backend.dreank.dto.request.user.UserUpdateRequest;
import gcu.backend.dreank.repository.UserStudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CalendarRepository calendarRepository;
    private final UserStudyRepository userStudyRepository;
    private final StudyRepository studyRepository;

    // CREATE
    @Transactional
    public void saveUser(UserCreateRequest request){
        userRepository.save(new User(request));
    }

    // READ
    @Transactional(readOnly = true)
    public boolean chkPwd(UserUpdateRequest request, Long id){
        User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return user.getPassword().equals(request.getPassword());
    }

    @Transactional(readOnly = true)
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    // UPDATE
    @Transactional
    public void updateUser(UserUpdateRequest request, Long id){
        User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        if(request.getNickname() != null) {
            user.setNickname(request.getNickname());
            System.out.println("Nickname updated to: " + request.getNickname());
        }
        if(request.getIntroduce() != null) {
            user.setIntroduce(request.getIntroduce());
            System.out.println("Introduce updated to: " + request.getIntroduce());
        }
        if(request.getNew_password() != null) {
            user.setPassword(request.getNew_password());
            System.out.println("Password updated to: " + request.getNew_password());
        }
        userRepository.save(user);
        System.out.println("User updated successfully");
    }

    // DELETE
    @Transactional
    public void deleteUser(long id){
        // Delete related entities first
        calendarRepository.deleteByUserId(id);
        studyRepository.deleteByUserId(id);
        userStudyRepository.deleteByUserId(id);

        User user = userRepository.findById(id)
                .orElseThrow(IllegalAccessError::new);
        userRepository.delete(user);
    }
}
