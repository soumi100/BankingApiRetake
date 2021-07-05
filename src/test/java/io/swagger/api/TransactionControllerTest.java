package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Transaction;
import io.swagger.model.TransactionDto;
import io.swagger.model.User;
import io.swagger.model.UserDTO;
import io.swagger.repository.TransactionRepository;
import io.swagger.service.AccountService;
import io.swagger.service.AuthenticationService;
import io.swagger.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.threeten.bp.LocalDate;
import org.threeten.bp.OffsetDateTime;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TransactionControllerTest {
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
    private TransactionRepository transactionRepository;

    @Autowired
    @MockBean
    TransactionDto transactionDto;

    @Autowired
    @MockBean
    private AuthenticationService authenticationService;

    private Transaction transaction;

    List<Transaction> transactionList = new ArrayList<>();

    @Autowired
    @MockBean
    private Transaction testTransaction;
    ObjectMapper objectMapper;


    private User user;

    @BeforeEach
    public void setup() {
        this.user = new User(2L, "prinsalvino", "test123", "prins", "alvino", "prinsalvino@gmx.com", LocalDate.of(1993, 8, 02), "Rijswijk", "1156AX", "Amsterdam", "062535199", User.TypeEnum.EMPLOYEE, true);

        this.testTransaction = new Transaction
                ("NL01INHO00000000010", "NL01INHO00000000080",
                        800d, "paris dinner", 1L, Transaction.TransactionTypeEnum.TRANSFER);

        transactionList.add(testTransaction);
        //transactionList.forEach(transactionRepository::save);

    }

    @Test
    @WithMockUser(username = "prinsalvino", password = "test123", authorities = "ROLE_EMPLOYEE")
    public void createTransactionShouldReturn200() throws Exception {
        objectMapper = new ObjectMapper();
        transactionDto = new TransactionDto();
        transactionDto.setId(1L);
        transactionDto.setAccountFrom(testTransaction.getAccountFrom());
        transactionDto.setAccountTo(testTransaction.getAccountTo());
        transactionDto.setAmount(testTransaction.getAmount());
        transactionDto.setDescription(testTransaction.getDescription());
        transactionDto.setUserPerformingId(testTransaction.getUserPerformingId());
        OffsetDateTime dtm = OffsetDateTime.now();
        transactionDto.setTimestamp(dtm);
        this.mockMvc
                .perform(
                        post("/transactions")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(testTransaction)))
                .andExpect(status().isCreated());
    }

}
