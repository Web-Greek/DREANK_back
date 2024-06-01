package gcu.backend.dreank.dto.request.user;

import gcu.backend.dreank.domain.study.Study;
import gcu.backend.dreank.domain.user.User;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCreateResponse {

    private String email;
    private String password;
    private String password_;

    private String nickname;
    private String name;
    private String introduce;
    private String phone;
    private String birth;

    //응답생성
    //public static gcu.backend.dreank.dto.request.user.UserCreateResponse of(final User user) {
    //    return new gcu.backend.dreank.dto.request.user.UserCreateResponse();
        //return new StudyCreateResponse(study)
    //}
}