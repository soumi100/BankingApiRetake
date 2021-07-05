package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.model.User;
import io.swagger.repository.AccountRepository;
import io.swagger.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.threeten.bp.LocalDate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TransactionServiceTest {
    @Autowired
    @Mock
    private TransactionRepository transactionRepository;

    @Autowired
    @InjectMocks
    private TransactionService transactionService;

    List<Transaction> transactionList = new ArrayList<>();
    private Transaction testTransaction;


    @BeforeEach
    public void setup() {

        this.testTransaction = new Transaction
                ("NL01INHO00000000010", "NL01INHO00000000080",
                        800d, "paris dinner", 1L, Transaction.TransactionTypeEnum.TRANSFER);

        transactionList.add(testTransaction);
    }

    @Test
    public void addTransaction(){
        transactionRepository.save(testTransaction);
        when(transactionRepository.findAll()).thenReturn(transactionList);
        List<Transaction> transactionList1 = transactionService.getTransactions();
        assertEquals(transactionList1, transactionList);
    }

    @Test
    public void getTransaction(){
        when(transactionRepository.findAll()).thenReturn(transactionList);
        List<Transaction> transactionList1 = transactionService.getTransactions();
        assertEquals(transactionList1, transactionList);
    }

    @Test
    public void getTransactionByIban(){
        transactionRepository.save(testTransaction);
        when(transactionRepository.findTransactionByAccountFrom("NL01")).thenReturn((transactionList));
        List<Transaction> transactions = transactionService.getTransactionByIban("NL01");
        assertEquals(transactions, testTransaction);
    }
}
