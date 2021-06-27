/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.25).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-02T11:15:57.209Z[GMT]")
@Validated
public interface UsersApi {

    @Operation(summary = "Create a new user", description = "Create a user", security = {
            @SecurityRequirement(name = "bearerAuth")}, tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created"),

            @ApiResponse(responseCode = "400", description = "bad request", content = @Content(schema = @Schema(implementation = String.class))),

            @ApiResponse(responseCode = "401", description = "Access token is missing or invalid"),

            @ApiResponse(responseCode = "404", description = "The specified resource was not found", content = @Content(schema = @Schema(implementation = String.class)))})
    @RequestMapping(value = "/users",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<String> createUser(@Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @Valid @RequestBody User body) throws JSONException;


    @Operation(summary = "Retrieve users that correspond to filter", description = "Gets users that correspond to filter", security = {
            @SecurityRequirement(name = "bearerAuth")}, tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class)))),

            @ApiResponse(responseCode = "400", description = "bad request", content = @Content(schema = @Schema(implementation = String.class))),

            @ApiResponse(responseCode = "401", description = "Access token is missing or invalid"),

            @ApiResponse(responseCode = "404", description = "The specified resource was not found", content = @Content(schema = @Schema(implementation = String.class)))})
    @RequestMapping(value = "/users",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<User>> getUsers(@Parameter(in = ParameterIn.QUERY, description = "Get users based on Last Name", schema = @Schema()) @Valid @RequestParam(value = "lastname", required = false) String lastname,
                                        @Parameter(in = ParameterIn.QUERY, description = "Get users based on Last Name", schema = @Schema()) @Valid @RequestParam(value = "firstname", required = false) String firstname,
                                        @Parameter(in = ParameterIn.QUERY, description = "Maximum numbers of items to return", schema = @Schema()) @Valid @RequestParam(value = "limit", required = false) Integer limit);

}

