package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Account;
import io.swagger.model.User;
import io.swagger.service.AccountService;
import io.swagger.service.AuthenticationService;
import io.swagger.service.UserService;
import io.swagger.util.JwtUtil;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;
import org.threeten.bp.LocalDate;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccountApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @MockBean
    private AccountService accountService;

    @Autowired
    @MockBean
    private AuthenticationService authenticationService;

    @Autowired
    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private User soumia;
    private Account account1;



    //@Before
    public void setUp() throws Exception {


        soumia = new User(1L, "SB", "pass123",
                "soumia", "bouhouri", "sou@gmx.com",
                LocalDate.of(1993, 8, 02),
                "Rijswijk", "2282JV", "Rijswijk", "062535199",
                User.TypeEnum.EMPLOYEE, true);

        account1 = new Account(1l, Account.TypeEnum.CURRENT,
                Account.CurrencyEnum.EUR, true, "NL01INHO00000000010", 9989);

    }

    @Test
    @WithMockUser(username = "prinsalvino", password = "test123", authorities = "ROLE_EMPLOYEE")
    public void callingAllUsersShouldReturnJsonArray() throws Exception {
        setUp();
        Mockito.when(authenticationService.isEmployee()).thenReturn(true);
        Mockito.when(accountService.getAccounts()).thenReturn(Arrays.asList(account1));
        this.mockMvc.perform(get("/accounts")).andExpect(status().isOk()).
                andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].iban").value(account1.getIban()));
    }


    @Test
    void getAccountByIBAN() {
    }

    @Test
    void addAccount() throws Exception {

    }

    @Test
    void deleteAccount() throws Exception {
    }

    @Test
    void updateAccount() {
    }
}