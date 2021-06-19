package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.model.AccountDto;
import io.swagger.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Random;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AuthenticationService authenticationService;

    public List<Account> getAccounts() {
        return (List<Account>) accountRepository.findAll();
    }

    public Account getAccountByIban(String iban) {
        Account account = accountRepository.getAccountByIban(iban);
        return account;
    }

    public ResponseEntity addAccount(Account account) throws IllegalAccessException {
        if (authenticationService.isEmployee()) {
            if (getAccountByIban(account.getIban()) == null) {
                Account NewAccount = new Account();
                NewAccount.setBalance(0);
                NewAccount.setActive(true);
                NewAccount.setCurrency(account.getCurrency());
                NewAccount.setIban(GenerateRandomIban());
                NewAccount.setType(account.getType());
                NewAccount.setUserId(account.getUserId());
                accountRepository.save(NewAccount);
                return new ResponseEntity(HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }


    }

    @DeleteMapping
    public ResponseEntity deleteAccount(String iban) {
        if (authenticationService.isEmployee()) {
            Account accountToDelete = accountRepository.getAccountByIban(iban);
            if (accountToDelete != null) {
                accountToDelete.setDeleted(true);
                accountRepository.save(accountToDelete);
                accountRepository.deleteAccountByIban(iban);
            } else {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }

        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @PutMapping
    public ResponseEntity updateAccount(AccountDto newUpdatedAccount, String iban) {

        if (authenticationService.isEmployee()) {
            if (accountRepository.getAccountByIban(iban) != null) {
                Account accountToUpdate = accountRepository.getAccountByIban(iban);
                accountToUpdate.setActive(newUpdatedAccount.getActive());
                accountToUpdate.setType(newUpdatedAccount.getType());
                accountToUpdate.setCurrency(newUpdatedAccount.getCurrency());
                accountRepository.save(accountToUpdate);
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    public String GenerateRandomIban() {
        Random random = new Random();
        StringBuilder iban = new StringBuilder("NL");
        for (int i = 0; i < 2; i++) {
            int num = random.nextInt(10);
            iban.append(num);
        }
        iban.append("INHO0");
        for (int i = 0; i < 10; i++) {
            int num = random.nextInt(10);
            iban.append(num);
        }
        return iban.toString();
    }

}
