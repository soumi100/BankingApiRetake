/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.25).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.model.TransactionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-02T11:15:57.209Z[GMT]")
@Validated
public interface TransactionsApi {

    @Operation(summary = "", description = "", security = {
            @SecurityRequirement(name = "bearerAuth")    }, tags={ "Transaction" })
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "list of returned transactions"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "bad request"),
            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "The specified resource was not found")
    })
    @RequestMapping(value = "/transactions",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<Transaction>> getTransactions( @Parameter(in = ParameterIn.QUERY, description = "The maximum numbers of items to return, exl" ,schema=@Schema()) @Valid @RequestParam(value = "limit", required = false) Integer limit);


    @Operation(summary = "get the account with the specific IBAN", description = "", tags={ "Transaction" })
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "entity corresponding to the requested resource"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "bad request"),
            @io.swagger.annotations.ApiResponse(code = 409, message = "already exists"),
            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "The requested resource could not be found")
    })
    @RequestMapping(value = "/transactions/{iban}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<Transaction>> getTransactionByIban(@Parameter(in = ParameterIn.PATH, description = "IBAN of the account to return",
            required=true, schema=@Schema()) @PathVariable("iban") String IBAN) throws NotFoundException;

/*
    @Operation(summary = "return transaction based of the user performing id", description = "", tags={ "Transaction" })
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "entity corresponding to the requested resource"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "bad request"),
            @io.swagger.annotations.ApiResponse(code = 409, message = "already exists"),
            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "The requested resource could not be found")
    })
    @RequestMapping(value = "/transactions/{userPerformingId}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<Transaction>> getTransactionByUserPerformingId(@Parameter(in = ParameterIn.PATH, description = "return transaction based of the user performing id",
            required=true, schema=@Schema()) @PathVariable("userPerformingId") String userPerformingId) throws NotFoundException;

*/
    //post
    @Operation(summary = "", description = "", security = {
            @SecurityRequirement(name = "bearerAuth")    }, tags={ "Transaction" })
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "list of returned transactions"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "bad request"),
            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "The specified resource was not found")
    })
    @RequestMapping(value = "/transactions",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    void createTransaction(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody TransactionDto body);

}

