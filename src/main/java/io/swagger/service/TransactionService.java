package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    public List<Transaction> getTransactions() {
        return (List<Transaction>) transactionRepository.findAll();
    }


    public List<Transaction> getTransactionByIban(String IBAN) //getting transactions from a specific user iban
    {
        return transactionRepository.findTransactionByAccountFrom(IBAN);
    }

    public boolean checkBalance(String iban, Double amount) {
        Account account = accountService.getAccountByIban(iban);
        if (account.getIban().equals(iban)) {
            // convert double amount type into integer
            Integer amt = amount.intValue();
            return account.getBalance() >= amt;
        } else {
            return false;
        }
    }

    public void createTransaction(Transaction transaction) {
        Account accFrom = accountService.getAccountByIban(transaction.getAccountFrom());
        Account accTo = accountService.getAccountByIban(transaction.getAccountTo());
        accFrom.setBalance(accFrom.getBalance() - transaction.getAmount().intValue());
        accTo.setBalance(accTo.getBalance() + transaction.getAmount().intValue());
        accountService.updateBalance(accFrom);
        accountService.updateBalance(accTo);
        transactionRepository.save(transaction);
    }


}