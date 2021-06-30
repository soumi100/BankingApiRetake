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


    @Operation(summary = "Get accounts with an offset", description = "A customer gets its accounts only  & Employee", security = {
            @SecurityRequirement(name = "bearerAuth")}, tags = {"employees", "customers"})
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "List of returned accounts"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "Bad request ")
    })
    @RequestMapping(value = "/accounts",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Account>> getAccounts(@RequestParam(value = "limit", required = false) Integer limit);


    @Operation(summary = "Get the account with the specific IBAN", description = "A customer gets his account only  & Employee", tags = {"employee", "customers"})
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Entity corresponding to the requested resource"),
            @io.swagger.annotations.ApiResponse(code = 403, message = "Forbidden"),
            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
    })
    @RequestMapping(value = "/accounts/{iban}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Account> getAccountByIBAN(@Parameter(in = ParameterIn.PATH, description = "IBAN of the account to return",
            required = true, schema = @Schema()) @PathVariable("iban") String IBAN) throws NotFoundException;

    @Operation(summary = "Open new account", description = "Creating an account |  Employee", security = {
            @SecurityRequirement(name = "bearerAuth")}, tags = {"employees"})
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 403, message = "Forbidden"),
            @io.swagger.annotations.ApiResponse(code = 200, message = "Account has been created successfully"),
    })
    @RequestMapping(value = "/accounts", consumes = {"application/json"}, method = RequestMethod.POST)
    ResponseEntity<Account> addAccount(@ApiParam(value = "") @Valid @RequestBody AccountDto account) throws IllegalAccessException;


    @Operation(summary = "Delete the account with the specific IBAN", description = "Delete an account | employees only", tags = {"employees"})
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Account has been deleted successfully "),
            @io.swagger.annotations.ApiResponse(code = 403, message = "Forbidden"),
    })
    @RequestMapping(value = "/accounts/{iban}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    void deleteAccount(@ApiParam(value = "", required = true) @PathVariable("iban") String iban
    ) throws NotFoundException;


    @Operation(summary = "Update the given account", description = "Customer ( its accounts ) & Employee ", security = {
            @SecurityRequirement(name = "bearerAuth")}, tags = {"employees", "customers"})
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Account has been modified successfully "),
            @io.swagger.annotations.ApiResponse(code = 403, message = "Forbidden"),
            @io.swagger.annotations.ApiResponse(code = 401, message = "Unauthorized"),
    })
    @RequestMapping(value = "/accounts/update/{iban}",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Account> updateAccount(@Parameter(in = ParameterIn.PATH, description = "Iban of the account", required = true, schema = @Schema()) @PathVariable("iban") String iban
            , @Parameter(in = ParameterIn.DEFAULT, description = "Body of the account to edit", schema = @Schema()) @Valid @RequestBody AccountDto body);


}

