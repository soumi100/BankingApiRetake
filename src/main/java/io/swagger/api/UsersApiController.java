package io.swagger.api;

import io.swagger.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-02T11:15:57.209Z[GMT]")
@RestController
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> createUser(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody User body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<User>> getUsers(@Parameter(in = ParameterIn.QUERY, description = "Get user that corresponds to userID" ,schema=@Schema()) @Valid @RequestParam(value = "userid", required = false) Integer userid,@Parameter(in = ParameterIn.QUERY, description = "Get user that has account with IBAN" ,schema=@Schema()) @Valid @RequestParam(value = "iban", required = false) Integer iban,@Parameter(in = ParameterIn.QUERY, description = "Get users based on Last Name" ,schema=@Schema()) @Valid @RequestParam(value = "lastname", required = false) String lastname,@Parameter(in = ParameterIn.QUERY, description = "Maximum numbers of items to return" ,schema=@Schema()) @Valid @RequestParam(value = "limit", required = false) Integer limit) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<User>>(objectMapper.readValue("[ {\n  \"lastName\" : \"bouhouri\",\n  \"birthdate\" : \"0013-07-16T00:00:00.000+00:00\",\n  \"address\" : \"huis te hoornkade 5\",\n  \"city\" : \"RijsWijk\",\n  \"active\" : true,\n  \"type\" : \"Customer\",\n  \"firstName\" : \"soumia\",\n  \"password\" : \"Password123\",\n  \"phoneNumber\" : \"0625351974\",\n  \"postalcode\" : \"2041 KP\",\n  \"id\" : 10000000001,\n  \"email\" : \"635335@gmail.com\",\n  \"username\" : \"user123\"\n}, {\n  \"lastName\" : \"bouhouri\",\n  \"birthdate\" : \"0013-07-16T00:00:00.000+00:00\",\n  \"address\" : \"huis te hoornkade 5\",\n  \"city\" : \"RijsWijk\",\n  \"active\" : true,\n  \"type\" : \"Customer\",\n  \"firstName\" : \"soumia\",\n  \"password\" : \"Password123\",\n  \"phoneNumber\" : \"0625351974\",\n  \"postalcode\" : \"2041 KP\",\n  \"id\" : 10000000001,\n  \"email\" : \"635335@gmail.com\",\n  \"username\" : \"user123\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<User>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
