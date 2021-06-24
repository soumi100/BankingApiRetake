package io.swagger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.User;
import io.swagger.model.UserDTO;
import io.swagger.repository.UserRepository;
import io.swagger.service.AuthenticationService;
import io.swagger.service.UserService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.threeten.bp.LocalDate;
import static org.mockito.BDDMockito.given;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;




@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @MockBean
    private UserService userService;

    @Autowired
    @MockBean
    private AuthenticationService authenticationService;

    private User user;

    @Before
    public void setup(){

    }

    @Test
    @WithMockUser(username = "prinsalvino", password = "test123", authorities = "ROLE_EMPLOYEE")
    public void callingAllUsersShouldReturn200() throws Exception {
        Mockito.when(authenticationService.isEmployee()).thenReturn(true);
        this.mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    public void loginShouldReturn200() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("prinsalvino");
        userDTO.setPassword("test123");
        this.mockMvc.perform(post("/Login").contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(objectMapper.writeValueAsString(userDTO))).andExpect(status().isOk());
    }
}
