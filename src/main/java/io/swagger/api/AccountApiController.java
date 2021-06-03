package io.swagger.api;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Account;
import io.swagger.model.Body;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
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
        List<Account> accounts = accountService.getAccounts();
        return new ResponseEntity<List<Account>>(accounts.subList(0,limit),HttpStatus.OK);
    }


    @Override
    public ResponseEntity <Void> deactivateAccount(@ApiParam(value = "IBAN to deactivate",required=true) @PathVariable("iban") String iban) {
         accountService.deactivateAccount(iban);
         return  null;
    }

    @Override
    public ResponseEntity<Account> getAccountByIBAN(String IBAN) throws NotFoundException {
        Account account = accountService.getAccountByIban(IBAN);
        return ResponseEntity
                .status(200)
                .body(account);
    }

    @Override
    public ResponseEntity<Account> updateAccount(@Valid Body body) {
        return null;
    }

    @Override
    public ResponseEntity<Account> addAccount(@Valid Account account) {
        accountService.addAccount(account);
        return new ResponseEntity<Account>(HttpStatus.OK);
    }

}
