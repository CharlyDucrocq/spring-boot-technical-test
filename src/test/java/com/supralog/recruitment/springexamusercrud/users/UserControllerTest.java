package com.supralog.recruitment.springexamusercrud.users;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.supralog.recruitment.springexamusercrud.users.models.Gender;
import com.supralog.recruitment.springexamusercrud.users.models.User;

import static com.supralog.recruitment.springexamusercrud.users.UserDtoValidationTest.buildSimpleInvalidUser;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({UserController.class})
public class UserControllerTest {
    private final ObjectMapper mapper = new ObjectMapper(){{registerModule(new JavaTimeModule());}};

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;
    @MockBean
    private UserRepository repository;

    @Test
    public void getAll_whenNoData_expectNoDataWithoutError() throws Exception {
        when(service.getAll()).thenReturn(List.of());

        this.mockMvc.perform(get("/users"))
                    .andExpect(status().isOk())
                    .andExpect(content().json("[]"));
    }

    @Test
    public void getAll_when2LambdaUser_expectUsersToBeReturnWithoutError() throws Exception {
        List<User> users = List.of(
                new User(1L, "name1", Gender.OTHER, "France", LocalDate.now(), null),
                new User(2L, "name2", Gender.FEMALE, "France", LocalDate.now(), "0701010101")
        );
        String usersJson = mapper.writeValueAsString(users);

        when(service.getAll()).thenReturn(users);

        this.mockMvc.perform(get("/users"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(usersJson));
    }

    @Test
    public void createUser_whenValidInput_expectUserToBeCreated() throws Exception {
        LocalDate todayBut18Before = LocalDate.now().minusYears(User.MINIMAL_AGE_REQUIRED);

        User newUser = new User(null, "test1", Gender.OTHER, "France", todayBut18Before, "0708090102");
        User newUserInDB = new User(1L, "test1", Gender.OTHER, "France", LocalDate.now(), "0708090102");
        String userJson = mapper.writeValueAsString(newUser);
        String resultJson = mapper.writeValueAsString(newUserInDB);

        when(service.create(any())).thenReturn(newUserInDB);

        this.mockMvc.perform(post("/users")
                                     .contentType(MediaType.APPLICATION_JSON)
                                     .content(userJson))
                    .andExpect(status().isOk())
                    .andExpect(content().json(resultJson));
        verify(service, times(1)).create(any());
    }

    @Test
    public void createUser_whenInvalidInput_expectBadRequestError() throws Exception {
        User newUser = buildSimpleInvalidUser();
        String userJson = mapper.writeValueAsString(newUser);

        this.mockMvc.perform(post("/users")
                                     .contentType(MediaType.APPLICATION_JSON)
                                     .content(userJson))
                    .andExpect(status().isBadRequest());
    }

}
