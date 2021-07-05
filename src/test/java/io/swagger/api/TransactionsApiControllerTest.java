package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Transaction;
import io.swagger.service.AccountService;
import io.swagger.service.AuthenticationService;
import io.swagger.service.TransactionService;

import io.swagger.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.threeten.bp.LocalDate;
import org.threeten.bp.OffsetDateTime;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionsApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @MockBean
    private AccountService accountService;

    @Autowired
    @MockBean
    private TransactionService transactionService;


    @Autowired
    @MockBean
    private AuthenticationService authenticationService;

    @Autowired
    @MockBean
    private UserService userService;

    private Transaction transaction;

    @BeforeEach
    public void setUp()  {
        this.transaction = new Transaction
                (1L, OffsetDateTime.now(),"NL01INHO00000000010", "NL01INHO00000000080",
                        700d, "greece dinner", 1L, Transaction.TransactionTypeEnum.TRANSFER);
    }

    //diifernce bw getting Transaction by a employee and user is that employee can get any trnasaction with any IBAN
    //but user will only get transaction by their own iban so we combine it here using an employee only for test
    @Test
    @WithMockUser(username = "SB", password = "pass123", authorities = "ROLE_EMPLOYEE")
    public void callingGetTransactionShouldReturnJsonArray() throws Exception {
        Mockito.when(authenticationService.isEmployee()).thenReturn(true);
        Mockito.when(transactionService.getTransactions()).thenReturn(Arrays.asList(transaction));
        this.mockMvc.perform(get("/transactions")).andExpect(status().isOk()).
                andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(transaction.getId()));
    }

    //if not employee, the request unauthorized
    @Test
    @WithMockUser(username = "SB", password = "pass123", authorities = "ROLE_EMPLOYEE")
    void getTransactionsByIBAN() throws Exception {
        Mockito.when(authenticationService.isEmployee()).thenReturn(true);
        Mockito.when(transactionService.getTransactionByIban("NL01INHO00000000010")).thenReturn(Arrays.asList(transaction));
        this.mockMvc
                .perform(get("/transactions/NL01INHO00000000010")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "SB", password = "pass123", authorities = "ROLE_EMPLOYEE")
    void addTransaction() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Mockito.when(authenticationService.isEmployee()).thenReturn(true);
        this.mockMvc
                .perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(transaction)))
                .andExpect(status().isCreated());

    }

    //if not employee, the request unauthorized
    @Test
    @WithMockUser(username = "SB", password = "pass123", authorities = "ROLE_CUSTOMER")
    public void callingGetTransactionsShouldReturnUnauthorized() throws Exception {
        Mockito.when(authenticationService.getCurrentUser().getId());
        Mockito.when(transactionService.getTransactions()).thenReturn(Arrays.asList(transaction));
        this.mockMvc
                .perform(get("/transactions")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isUnauthorized());
    }

}