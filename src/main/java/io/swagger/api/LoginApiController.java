package io.swagger.api;

import io.swagger.model.Body1;
import io.swagger.model.InlineResponse200;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.User;
import io.swagger.service.JwtUtil;
import io.swagger.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-06-02T11:15:57.209Z[GMT]")
@RestController
public class LoginApiController implements LoginApi {

    private static final Logger log = LoggerFactory.getLogger(LoginApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    JwtUtil jwtUtill;

    @Autowired
    UserService userService;

    @org.springframework.beans.factory.annotation.Autowired
    public LoginApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<InlineResponse200> login(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody Body1 body) {
        String accept = request.getHeader("Accept");
        InlineResponse200 response200 = new InlineResponse200();
            String username = body.getUsername();
            String password = body.getPassword();
            User user = userService.getLogin(username,password);
            if (user != null){
                final String jwt = jwtUtill.generateToken(user);
                response200.setToken(jwt);
                response200.setId(user.getId().intValue());
                return new ResponseEntity<InlineResponse200>(response200, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<InlineResponse200>(HttpStatus.UNAUTHORIZED);
            }

    }

}
