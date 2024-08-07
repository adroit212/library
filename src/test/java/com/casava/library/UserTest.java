package com.casava.library;

import com.casava.library.controller.UserController;
import com.casava.library.domain.User;
import com.casava.library.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class UserTest {
    @InjectMocks
    private UserController userController;
    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createUserTest() throws Exception {
        mockMvc.perform(post("/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(buildUser())))
                .andExpect(status().isOk());
    }

    @Test
    public void updateUserTest() throws Exception{
        mockMvc.perform(put("/users/66b363f21821a66a4d2dddb7")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(buildUser())))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUserTest() throws Exception{
        mockMvc.perform(delete("/users/66b363f21821a66a4d2dddb7")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    private User buildUser(){
        User user = new User();
        user.setEmail("test@email.com");
        user.setFullName("Test Tester");
        user.setMembershipDate(LocalDate.now());

        return user;
    }
}
