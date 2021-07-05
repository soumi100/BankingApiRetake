package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Account;
import io.swagger.model.User;
import io.swagger.service.AccountService;
import io.swagger.service.AuthenticationService;
import io.swagger.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.threeten.bp.LocalDate;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class AccountApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @MockBean
    private AccountService accountService;

    @Autowired
    @MockBean
    private UserService userService;


    @Autowired
    @MockBean
    private AuthenticationService authenticationService;

    private Account account1;
    private User user;

    @BeforeEach
    public void setUp()  {
        account1 = new Account(1l, Account.TypeEnum.CURRENT,
                Account.CurrencyEnum.EUR, true, "NL01INHO00000000010", 9989);
        user = new User(2L, "prinsalvino", "test123", "prins", "alvino", "prinsalvino@gmx.com", LocalDate.of(1993, 8, 02), "Rijswijk", "1156AX", "Amsterdam", "062535199", User.TypeEnum.EMPLOYEE, true);


    }

    @Test
    @WithMockUser(username = "SB", password = "pass123", authorities = "ROLE_EMPLOYEE")
    public void callingGetAccountsByAnEmployeeShouldReturnJsonArray() throws Exception {
        Mockito.when(authenticationService.isEmployee()).thenReturn(true);
        Mockito.when(accountService.getAccounts()).thenReturn(Arrays.asList(account1));
        this.mockMvc.perform(get("/accounts")).andExpect(status().isOk()).
                andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].userId").value(account1.getUserId()));
    }

    @Test
    @WithMockUser(username = "SB", password = "pass123", authorities = "ROLE_EMPLOYEE")
    void getAccountByIBAN() throws Exception {
        Mockito.when(authenticationService.isEmployee()).thenReturn(true);
        Mockito.when(accountService.getAccountByIban("NL01INHO00000000010")).thenReturn(account1);
        this.mockMvc
                .perform(get("/accounts/NL01INHO00000000010")
                 .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "SB", password = "pass123", authorities = "ROLE_EMPLOYEE")
    void addAccount() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Mockito.when(authenticationService.isEmployee()).thenReturn(true);
        this.mockMvc
                .perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(account1)))
                        .andExpect(status().isCreated());

    }

    @Test
    @WithMockUser(username = "SB", password = "pass123", authorities = "ROLE_EMPLOYEE")
    void deleteAccount() throws Exception {
        Mockito.when(authenticationService.isEmployee()).thenReturn(true);
        Mockito.when(accountService.getAccountByIban("NL01INHO00000000010")).thenReturn(account1);
        this.mockMvc
                .perform(delete("/accounts/NL01INHO00000000010")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "SB", password = "123", authorities = "ROLE_CUSTOMER")
    public void callingGetAccountByIbanShouldReturn403() throws Exception {
        Mockito.when(authenticationService.isEmployee()).thenReturn(false);
        Mockito.when( authenticationService.getCurrentUser()).thenReturn(user);
        Mockito.when(accountService.getAccountByIban("NL01INHO00000000010")).thenReturn(account1);
        this.mockMvc.perform(get("/accounts/NL01INHO00000000010")).andExpect(status().isForbidden());
    }

}