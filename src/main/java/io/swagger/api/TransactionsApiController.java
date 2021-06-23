package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.Body2;
import io.swagger.model.Transaction;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-02T11:15:57.209Z[GMT]")
@RestController
public class TransactionsApiController implements TransactionsApi {

    private static final Logger log = LoggerFactory.getLogger(TransactionsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public TransactionsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> createTransaction(@Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @Valid @RequestBody Body2 body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Transaction>> getTransactions(@Parameter(in = ParameterIn.QUERY, description = "Get transaction that corresponds to IBAN", schema = @Schema()) @Valid @RequestParam(value = "iban", required = false) String iban, @Parameter(in = ParameterIn.QUERY, description = "transactions to date", schema = @Schema()) @Valid @RequestParam(value = "dateTo", required = false) String dateTo, @Parameter(in = ParameterIn.QUERY, description = "transactions from date", schema = @Schema()) @Valid @RequestParam(value = "dateFrom", required = false) String dateFrom, @Parameter(in = ParameterIn.QUERY, description = "The numbers of items to return", schema = @Schema()) @Valid @RequestParam(value = "limit", required = false) Integer limit) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Transaction>>(objectMapper.readValue("[ {\n  \"transactionType\" : \"Deposit\",\n  \"accountTo\" : \"NL01INHO0000000000\",\n  \"amount\" : 100,\n  \"userPerformingId\" : 10000000001,\n  \"description\" : \"Money for your new RB-17\",\n  \"id\" : 10000000001,\n  \"accountFrom\" : \"NL01INHO0000000000\",\n  \"timestamp\" : \"2021-05-07T12:32:28Z\"\n}, {\n  \"transactionType\" : \"Deposit\",\n  \"accountTo\" : \"NL01INHO0000000000\",\n  \"amount\" : 100,\n  \"userPerformingId\" : 10000000001,\n  \"description\" : \"Money for your new RB-17\",\n  \"id\" : 10000000001,\n  \"accountFrom\" : \"NL01INHO0000000000\",\n  \"timestamp\" : \"2021-05-07T12:32:28Z\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Transaction>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
