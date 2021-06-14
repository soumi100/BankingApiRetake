package io.swagger.api;

import io.swagger.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.service.AuthenticationService;
import io.swagger.service.UserService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
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
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-02T11:15:57.209Z[GMT]")
@RestController
@Controller
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> createUser(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody User body) {
        System.out.println(authenticationService.isEmployee());
        if (authenticationService.isEmployee() == true) {

            if(body.getType() == null){
                body.setType(User.TypeEnum.CUSTOMER);
            }
            System.out.println(body.getBirthdate());
            userService.createUser(body);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity<List<User>> getUsers(@Parameter(in = ParameterIn.QUERY, description = "Get users based on Last Name" ,
                                                       schema=@Schema()) @Valid @RequestParam(value = "lastname", required = false) String lastname,
                                               @Parameter(in = ParameterIn.QUERY, description = "Maximum numbers of items to return" ,
                                                       schema=@Schema()) @Valid @RequestParam(value = "limit", required = false) Integer limit)
    {
        if (authenticationService.isEmployee() == true){
            if(limit != null && lastname == null){
                return new ResponseEntity<List<User>>(userService.getAllUserWithLimit(limit), HttpStatus.OK);
            }
            else if(limit == null && lastname != null){
                return new ResponseEntity<List<User>>((List<User>) userService.getByLastName(lastname), HttpStatus.OK);
            }
            else if(limit != null && lastname != null){
                System.out.println(lastname);
                System.out.println(limit);
                return new ResponseEntity<List<User>>((List<User>) userService.getByLastNameWithLimit(lastname, limit), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<List<User>>(userService.getAllUser(), HttpStatus.OK);
            }

        }
        return new ResponseEntity<List<User>>(HttpStatus.FORBIDDEN);
    }

}
