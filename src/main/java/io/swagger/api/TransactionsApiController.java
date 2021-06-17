package io.swagger.api;

import io.swagger.model.Account;
import io.swagger.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.TransactionDto;
import io.swagger.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneId;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-02T11:15:57.209Z[GMT]")
@RestController
public class TransactionsApiController implements TransactionsApi {

    @Autowired
    private TransactionService transactionService;

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
        List<Transaction> transactions = (List<Transaction>) transactionService.getTransactions();
        Integer defaultLimit= 2;
        if (limit > 0 && limit !=null ){
            return new ResponseEntity<List<Transaction>>(transactions.subList(0,limit),HttpStatus.OK)
                    .status(200)
                    .body(transactions.subList(0,limit));
        }else
        {
            return new ResponseEntity<List<Transaction>>(transactions.subList(0,defaultLimit),HttpStatus.OK)
                    .status(200)
                    .body(transactions.subList(0,defaultLimit));
        }

    }

    @Override
    public ResponseEntity<List<Transaction>> getTransactionByIban(String iban) throws NotFoundException {
        List<Transaction> transactions = (List<Transaction>) transactionService.getTransactionByIban(iban) ;
        return ResponseEntity
                .status(200)
                .body(transactions);
    }


    public void createTransaction(TransactionDto transactionDto) {
                Transaction NewTransaction= new Transaction();
                NewTransaction.setAccountFrom(transactionDto.getAccountFrom());
                NewTransaction.setAccountTo(transactionDto.getAccountTo());
                NewTransaction.setAmount(transactionDto.getAmount());
                NewTransaction.setDescription(transactionDto.getDescription());

                //*** addd it to dto
              NewTransaction.setUserPerformingId(5l);
              NewTransaction.setTransactionType(Transaction.TransactionTypeEnum.TRANSFER);

              //timestamp config
        /*
              long timestamp = Long.parseLong("1498329000000L");
              ZoneId zone = ZoneId.of("Europe/Brussels");
              Instant instant = Instant.ofEpochMilli(timestamp);
              OffsetDateTime dateTime = OffsetDateTime.ofInstant(instant, zone);

        */
              OffsetDateTime dtm =
                OffsetDateTime.parse("2017-04-01T00:00:00+00:00");

              NewTransaction.setTimestamp(dtm);
              transactionService.createTransaction(NewTransaction);

    }

}
