/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.25).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Account;
import io.swagger.model.AccountDto;
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
public interface AccountApi {

    @Operation(summary = "get accounts with an offset", description = "Calling this allows you to fetch all accounts", security = {
            @SecurityRequirement(name = "bearerAuth")}, tags = {"Accounts"})
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "list of returned accounts"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "bad request"),
            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
            @io.swagger.annotations.ApiResponse(code = 409, message = "already exists"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "The specified resource was not found")
    })
    @RequestMapping(value = "/accounts",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Account>> getAccounts(@Parameter(in = ParameterIn.QUERY, description = "The maximum numbers of items to return, exl", schema = @Schema()) @Valid @RequestParam(value = "limit", required = false) Integer limit);


    @Operation(summary = "get the account with the specific IBAN", description = "", tags = {"Accounts"})
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "entity corresponding to the requested resource"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "bad request"),
            @io.swagger.annotations.ApiResponse(code = 409, message = "already exists"),
            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "The requested resource could not be found")
    })
    @RequestMapping(value = "/accounts/{iban}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Account> getAccountByIBAN(@Parameter(in = ParameterIn.PATH, description = "IBAN of the account to return",
            required = true, schema = @Schema()) @PathVariable("iban") String IBAN) throws NotFoundException;

    @Operation(summary = "create new account", description = "Calling this will create a new account", security = {
            @SecurityRequirement(name = "bearerAuth")}, tags = {"Accounts"})
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 201, message = "creating a new resource."),
            @io.swagger.annotations.ApiResponse(code = 400, message = "bad request"),
            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
            @io.swagger.annotations.ApiResponse(code = 409, message = "already exists"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "The specified resource was not found")
    })
    @RequestMapping(value = "/accounts", consumes = {"application/json"}, method = RequestMethod.POST)
    ResponseEntity<Account> addAccount(@ApiParam(value = "") @Valid @RequestBody Account account) throws IllegalAccessException;


    @Operation(summary = "delete the account with the specific IBAN", description = "", tags = {"Accounts"})
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Succesfull"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "bad request", response = String.class),
            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "The requested resource could not be found", response = String.class)})
    @RequestMapping(value = "/accounts/{iban}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    void deleteAccount(@ApiParam(value = "IBAN to delete", required = true) @PathVariable("iban") String iban
    );


    @Operation(summary = "update the given account", description = "update account ", security = {
            @SecurityRequirement(name = "bearerAuth")}, tags = {"Accounts"})
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "list of returned accounts"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "bad request"),
            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
            @io.swagger.annotations.ApiResponse(code = 409, message = "already exists"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "The specified resource was not found")
    })
    @RequestMapping(value = "/accounts/update/{iban}",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Account> updateAccount(@Parameter(in = ParameterIn.PATH, description = "", required = true, schema = @Schema()) @PathVariable("iban") String iban
            , @Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @Valid @RequestBody AccountDto body);

}

