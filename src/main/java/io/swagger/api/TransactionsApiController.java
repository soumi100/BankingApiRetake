package io.swagger.api;

import io.swagger.model.Account;
import io.swagger.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.TransactionDto;
import io.swagger.service.AccountService;
import io.swagger.service.AuthenticationService;
import io.swagger.service.TransactionService;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneId;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-02T11:15:57.209Z[GMT]")
@RestController
public class TransactionsApiController implements TransactionsApi {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    AccountService accountService;

    private static final Logger log = LoggerFactory.getLogger(TransactionsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public TransactionsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }


    @Override
    public ResponseEntity<List<Transaction>> getTransactions(Integer limit) {
        if(authenticationService.isEmployee()){
            List<Transaction> transactions =  transactionService.getTransactions();
            if (transactions.size() < limit || limit == null){
                limit = transactions.size();
            }
            return new ResponseEntity<List<Transaction>>(transactions.subList(0,limit),HttpStatus.OK)
                    .status(200)
                    .body(transactions.subList(0,limit));
        }
        return new ResponseEntity<List<Transaction>>(HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<List<Transaction>> getTransactionByIban(String iban) throws NotFoundException {
        if (authenticationService.isEmployee()){
            List<Transaction> transactions = (List<Transaction>) transactionService.getTransactionByIban(iban) ;
            return ResponseEntity
                    .status(200)
                    .body(transactions);
        }
        return new ResponseEntity<List<Transaction>>(HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<String> createTransaction(@RequestBody TransactionDto transactionDto) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        if(authenticationService.isEmployee()){
            if (setTransaction(transactionDto)){
                jsonObject.put("message", "Success");
                return new ResponseEntity<String>(jsonObject.toString(),HttpStatus.CREATED);
            }
            else{
                jsonObject.put("message", "There is something wrong in your body, your balance maybe lower than your wished transfer amount");
                return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.BAD_REQUEST);
            }
        }
        else{
            Account account = accountService.getAccountByUserId(authenticationService.getCurrentUser().getId());
            if (account.getIban() == transactionDto.getAccountFrom()){
                if(setTransaction(transactionDto)){
                    jsonObject.put("message", "Success");
                    return new ResponseEntity<String>(jsonObject.toString(),HttpStatus.CREATED);
                }
                else {
                    jsonObject.put("message", "There is something wrong in your body, " +
                            "your balance maybe lower than your wished transfer amount or your IBAN doesnot match" +
                            "your IBAN from");
                    return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.BAD_REQUEST);
                }
            }
            else if(transactionDto.getAccountFrom()==null) {
                transactionDto.setAccountFrom(account.getIban());
                if (setTransaction(transactionDto)) {
                    jsonObject.put("message", "Success");
                    return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.CREATED);
                } else {
                    jsonObject.put("message", "There is something wrong in your body, " +
                            "your balance maybe lower than your wished transfer amount");
                    return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.BAD_REQUEST);
                }
            }
            else {
                jsonObject.put("message", "There is something wrong in your body, " +
                        "your balance maybe lower than your wished transfer amount");
                return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.BAD_REQUEST);
            }
        }
    }

//    // Not sure if user can deposit or withdraw using API
//    @Override
//    public ResponseEntity<String> doDeposit(double value) throws JSONException {
//        JSONObject jsonObject = new JSONObject();
//        Account account = accountService.getAccountByUserId(authenticationService.getCurrentUser().getId());
//        transactionService.deposit(account, value);
//        jsonObject.put("message", "Success");
//        return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.CREATED);
//    }
//
//    @Override
//    public ResponseEntity<String> doWithdraw(double value) throws JSONException {
//        JSONObject jsonObject = new JSONObject();
//        Account account = accountService.getAccountByUserId(authenticationService.getCurrentUser().getId());
//        transactionService.withdraw(account, value);
//        jsonObject.put("message", "Success");
//        return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.CREATED);    }

    private boolean setTransaction(TransactionDto transactionDto) {
        if (transactionService.checkBalance(transactionDto.getAccountFrom(), transactionDto.getAmount())) {

            Transaction newTransaction = new Transaction();
            newTransaction.setAccountFrom(transactionDto.getAccountFrom());
            newTransaction.setAccountTo(transactionDto.getAccountTo());
            newTransaction.setAmount(transactionDto.getAmount());
            newTransaction.setDescription(transactionDto.getDescription());
            newTransaction.setUserPerformingId(authenticationService.getCurrentUser().getId());
            newTransaction.setTransactionType(Transaction.TransactionTypeEnum.TRANSFER);

            OffsetDateTime dtm = OffsetDateTime.now();
            newTransaction.setTimestamp(dtm);
            transactionService.createTransaction(newTransaction);
            return true;
        } else {
            return false;
        }
    }
}