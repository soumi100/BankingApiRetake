package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getTransactions()
    {
        return (List<Transaction>) transactionRepository.findAll();
    }


    public List<Transaction> getTransactionByIban(String IBAN) //getting transactions from a specific user iban
    {
        //CONFIG USER ACCESS LATER
        return (List<Transaction>) transactionRepository.getTransactionByIban(IBAN);
    }

    public void createTransaction(Transaction transaction){
        transactionRepository.save(transaction);
    }


}
