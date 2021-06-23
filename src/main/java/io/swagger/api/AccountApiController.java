package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Account;
import io.swagger.model.AccountDto;
import io.swagger.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-02T11:15:57.209Z[GMT]")
@RestController
public class AccountApiController implements AccountApi {

    @Autowired
    private AccountService accountService;

    private static final Logger log = LoggerFactory.getLogger(AccountApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public AccountApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Override
    public ResponseEntity<List<Account>> getAccounts(@Valid Integer limit) {
        Integer defaultLimit = 3;
        List<Account> accounts = accountService.getAccounts();
        if (limit == 0 || limit == null){
            return new ResponseEntity<List<Account>>(accounts.subList(0,defaultLimit),HttpStatus.OK)
                    .status(200)
                    .body(accounts.subList(0,defaultLimit));
        }
        else  {
            return new ResponseEntity<List<Account>>(accounts.subList(0,limit),HttpStatus.OK)
                    .status(200)
                    .body(accounts.subList(0,limit));
        }
    }


    @Override
    public ResponseEntity<Account> getAccountByIBAN(String iban) throws NotFoundException {
        Account account = accountService.getAccountByIban(iban);
        return ResponseEntity
                .status(200)
                .body(account);
    }

    @Override
    public ResponseEntity<Account> addAccount(@Valid Account account) {
        Account NewAccount =  accountService.addAccount(account);
        return ResponseEntity
                .status(201)
                .body(NewAccount);
    }


    @Override
    public void deleteAccount(String iban) {
        accountService.deleteAccount(iban);
    }

    @Override
    public ResponseEntity<Account> updateAccount(String IBAN, @Valid AccountDto body) {
        Account account = accountService.updateAccount(body, IBAN);
        return ResponseEntity
                .status(200)
                .body(account);
    }
}
