package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.model.AccountDto;
import io.swagger.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Random;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAccounts()
    {
        return (List<Account>) accountRepository.findAll();
    }

    public Account getAccountByIban(String IBAN)  {
        Account account = accountRepository.getAccountByIban(IBAN);
        return  account;
    }

    public Account addAccount(Account account) {
        Account NewAccount = new Account();
        NewAccount.setBalance(0);
        NewAccount.setActive(true);
        NewAccount.setCurrency(account.getCurrency());
        NewAccount.setIban(GenerateRandomIban());
        NewAccount.setType(account.getType());
        NewAccount.setUserId(account.getUserId());
        accountRepository.save(NewAccount);
        return account;
    }

    @DeleteMapping
    public void deleteAccount(String IBAN)
    {
        Account accountToDelete = accountRepository.getAccountByIban(IBAN);
       // accountToDelete.setDeleted(true);
        accountRepository.save(accountToDelete);
    }

    @PutMapping
    public Account updateAccount(AccountDto newUpdatedAccount, String IBAN)
    {
        Account accountToUpdate = accountRepository.getAccountByIban(IBAN);
        accountToUpdate.setActive(newUpdatedAccount.getActive());
        accountToUpdate.setType(newUpdatedAccount.getType());
        accountToUpdate.setCurrency(newUpdatedAccount.getCurrency());
        accountRepository.save(accountToUpdate);
        return accountToUpdate;
    }

    public String GenerateRandomIban() {
        Random random = new Random();
        StringBuilder IBAN = new StringBuilder("NL");
        for (int i = 0; i < 2; i++) {
            int num = random.nextInt(10);
            IBAN.append(num);
        }
        IBAN.append("INHO0");
        for (int i = 0; i < 10; i++) {
            int num = random.nextInt(10);
            IBAN.append(num);
        }
        return  IBAN.toString();
    }

}
