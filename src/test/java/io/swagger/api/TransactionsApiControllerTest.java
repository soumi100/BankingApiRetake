package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.model.User;
import io.swagger.repository.TransactionRepository;
import io.swagger.service.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.threeten.bp.LocalDate;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TransactionsApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @MockBean
    private TransactionService transactionService;


    @Autowired
    @MockBean
    private AuthenticationService authenticationService;

    @Autowired
    @MockBean
    private UserService userService;

    @Autowired
    @MockBean
    private TransactionRepository transactionRepository;

    private Transaction transaction;

    @Autowired
    PasswordEncoder encoder;

    @BeforeEach
    public void setUp()  {
        this.transaction = new Transaction
                ("NL01INHO00000000010", "NL01INHO00000000080",
                        700d, "greece dinner", 1L, Transaction.TransactionTypeEnum.TRANSFER);
    }
    @Test
    @WithMockUser(username = "SB", password = "pass123", authorities = "ROLE_EMPLOYEE")
    public void callingGetTransactionShouldReturn200() throws Exception {
        Mockito.when(authenticationService.isEmployee()).thenReturn(true);
        Mockito.when(transactionService.getTransactions()).thenReturn(Arrays.asList(transaction));
        this.mockMvc.perform(get("/accounts")).andExpect(status().isOk()).
                andExpect(jsonPath("$", Matchers.hasSize(1)));
    }

}