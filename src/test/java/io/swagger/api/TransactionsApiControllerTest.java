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
import java.util.Collections;
import java.util.List;

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
    @WithMockUser(username = "prinsalvino", password = "test123", authorities = "ROLE_EMPLOYEE")
    public void test() throws Exception {
        Mockito.when(authenticationService.isEmployee()).thenReturn(false);
        Mockito.when(transactionService.getTransactions()).thenReturn(Arrays.asList(transaction));
        this.mockMvc.perform(get("/transactions")).andExpect(status().isOk()).
                andExpect(jsonPath("$", Matchers.hasSize(1)));
    }

}