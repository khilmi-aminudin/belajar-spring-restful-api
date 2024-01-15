package programmerzamannow.restful.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import programmerzamannow.restful.entity.User;
import programmerzamannow.restful.model.LoginUserRequest;
import programmerzamannow.restful.model.TokenResponse;
import programmerzamannow.restful.model.WebResponse;
import programmerzamannow.restful.repository.UserRepository;
import programmerzamannow.restful.security.BCrypt;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    void setup(){
        userRepository.deleteAll();
    }
    
    @Test
    void loginFailedUserNotFound() throws Exception{
        LoginUserRequest request = new LoginUserRequest();
        request.setUsername("usernameSalah");
        request.setPassword("");

        mockMvc.perform(
                post("/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});

            assertNotNull(response.getErrors());
        });
    }

    @Test
    void loginFailedWrongPassword() throws Exception{
        User user = new User();
        user.setUsername("test");
        user.setPassword(BCrypt.hashpw("rahasia",  BCrypt.gensalt()));
        user.setName("test rahasia");
        userRepository.save(user);

        LoginUserRequest request = new LoginUserRequest();
        request.setUsername(user.getUsername());
        request.setPassword("bukanRahasia");

        mockMvc.perform(
                post("/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});

            assertNotNull(response.getErrors());
        });
    }

    @Test
    void loginSuccess() throws Exception{
        User user = new User();
        user.setUsername("test");
        user.setPassword(BCrypt.hashpw("rahasia",  BCrypt.gensalt()));
        user.setName("test rahasia");
        userRepository.save(user);

        LoginUserRequest request = new LoginUserRequest();
        request.setUsername(user.getUsername());
        request.setPassword("rahasia");

        mockMvc.perform(
                post("/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<TokenResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});

            assertNotNull(response.getData().getToken());
            assertNotNull(response.getData().getExpiredAt());

            assertNull(response.getErrors());

            User userInDb = userRepository.findById("test").orElse(null);
            assertEquals(userInDb.getToken(), response.getData().getToken());
            assertEquals(userInDb.getTokenExpiredAt(), response.getData().getExpiredAt());
        });
    }
}