package gcu.backend.dreank;
/* test 코드임

import com.fasterxml.jackson.databind.ObjectMapper;
import gcu.backend.dreank.controller.user.UserController;
import gcu.backend.dreank.dto.request.user.UserCreateRequest;
import gcu.backend.dreank.dto.request.user.UserUpdateRequest;
import gcu.backend.dreank.service.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//UserController에 대한 Component Test
@WebMvcTest(UserController.class)
public class UserComponentTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @DisplayName("[POST] Sign in")
    @Test
    public void createUserTest() throws Exception {
        UserCreateRequest request = new UserCreateRequest();
        request.setEmail("test@gmail.com");
        request.setPassword("123456");
        request.setNickname("user");
        request.setName("kim");
        request.setPhone("01000011123");
        request.setBirth("2000-01-01");
        request.setIntroduce("Hello!");

        mvc.perform(post("/user")
                        .content(new ObjectMapper().writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)) // Content-Type 설정
                .andExpect(status().isOk())
                .andDo(print());

    }

    @DisplayName("[PATCH] - User 정보 수정")
    @Test
    public void updateUserTest() throws Exception {
        UserUpdateRequest request = new UserUpdateRequest();
        request.setNickname("newNickname");

        mvc.perform(MockMvcRequestBuilders.patch("/user/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request.toJson()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
*/
