package io.swagger.service;

import io.swagger.api.NotFoundException;
import io.swagger.model.Account;
import io.swagger.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAccounts()
    {
        return (List<Account>) accountRepository.findAll();
    }

    public Account addAccount(Account account) {
        accountRepository.save(account);
        return account;
    }
    public Account getAccountByIban(String IBAN)  {
        Account account = accountRepository.getAccountByIban(IBAN);
        return  account;
    }

    @PutMapping
    public void deactivateAccount(String iban)
    {
        // soft delete
        Account accountToDeactivate = accountRepository.getAccountByIban(iban);
        accountToDeactivate.setActive(false);
        accountRepository.save(accountToDeactivate);
    }

}
