package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.model.User;
import io.swagger.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.threeten.bp.LocalDate;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @Autowired
    @InjectMocks
    private AccountService accountService;

    List<Account> accountList = new ArrayList<>();
    private Account testAccount;

    @Autowired
    PasswordEncoder encoder;

    @BeforeEach
    public void setup() {

        User soumia =  new User(1L, "SB", "pass123",
                "soumia", "bouhouri", "sou@gmx.com",
                LocalDate.of(1993, 8, 02),
                "Rijswijk", "2282JV", "Rijswijk", "062535199",
                User.TypeEnum.EMPLOYEE, true);

        this.testAccount = new Account(1l, Account.TypeEnum.CURRENT,
                Account.CurrencyEnum.EUR, true, "NL01INHO00000000010", 9989);

        accountList.add(testAccount);
    }

    @Test
    public void addAccount(){
        accountRepository.save(testAccount);
        when(accountRepository.findAll()).thenReturn(accountList);
        List<Account> accountList1 = accountService.getAccounts();
        assertEquals(accountList1, accountList);
    }

    @Test
    public void getAccounts(){
        when(accountRepository.findAll()).thenReturn(accountList);
        List<Account> accountList1 = accountService.getAccounts();
        assertEquals(accountList1, accountList);
    }

    @Test
    public void getAccountByIban(){
        accountRepository.save(testAccount);

        when(accountRepository.findById("NL01")).thenReturn(java.util.Optional.ofNullable(testAccount));
        Account account = accountService.getAccountByIban("NL01");
        assertEquals(account, testAccount);
    }



}