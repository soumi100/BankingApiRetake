package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Account;
import io.swagger.model.User;
import io.swagger.service.AccountService;
import io.swagger.service.UserService;
import io.swagger.util.JwtUtil;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;
import org.threeten.bp.LocalDate;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AccountApiControllerTest {

    private static final String testIban1 = "TESTIBAN001";
    private static final String testIban2 = "TESTIBAN002";
    private static final Integer userId = 01;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private ObjectMapper objectMapper;

    private User soumia;
    private Account account1;

    private String jwtToken;


    @Before
    public void setUp() throws Exception {


        soumia = new User(1L, "SB", encoder.encode("pass123"),
                "soumia", "bouhouri", "sou@gmx.com",
                LocalDate.of(1993, 8, 02),
                "Rijswijk", "2282JV", "Rijswijk", "062535199",
                User.TypeEnum.EMPLOYEE, true);

        account1 = new Account(1l, Account.TypeEnum.CURRENT,
                Account.CurrencyEnum.EUR, true, "NL01INHO00000000010", 9989);

        accountService.addAccount(account1);
        userService.createUser(soumia);

        MvcResult result = mvc.perform(post("/Login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"SB\", \"password\": \"pass123\"}"))
                .andExpect(status().isOk()
                ).andReturn();

        String contentAsString = result.getResponse().getContentAsString();
        JwtUtil login = objectMapper.readValue(contentAsString, JwtUtil.class);
        this.jwtToken = login.generateToken(soumia.getUsername(), soumia.getType());

    }

    @Test
    void getAccounts() throws Exception {
        Iterable<Account> accountList = Arrays.asList(account1);
        given(accountService.getAccounts()).willReturn((List<Account>) accountList);
        mvc.perform(get("/accounts"))
                .andExpect(status().isOk());
    }

    @Test
    void getAccountByIBAN() {
    }

    @Test
    void addAccount() throws Exception {

    }

    @Test
    void deleteAccount() throws Exception {
        mvc.perform(delete("/accounts/NL71INHO09631273")
                .header("Authorization", "Bearer " + this.jwtToken)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    void updateAccount() {
        System.out.println(this.jwtToken);
    }
}