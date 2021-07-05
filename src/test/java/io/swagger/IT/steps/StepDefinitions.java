package io.swagger.IT.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.swagger.model.User;
import io.swagger.model.UserDTO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.runner.Request;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.threeten.bp.LocalDate;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;

public class StepDefinitions {
    RestTemplate template = new RestTemplate();
    ResponseEntity<String> responseEntity;
    String response;
    HttpClient httpClient = HttpClient.newHttpClient();


    HttpHeaders headers = new HttpHeaders();
    String baseUrl = "http://localhost:8484";
    String token;

    @When("I retrieve all users")
    public void iRetrieveAllUsers() throws URISyntaxException {

        URI uri = new URI(baseUrl);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        responseEntity = template.getForEntity(uri, String.class);
    }

    @Then("I get http status {int}")
    public void iGetHttpStatus(int status) {
        Assert.assertEquals(responseEntity.getStatusCodeValue(), status);
    }

    @Then("I get a list of {int} guitars")
    public void iGetAListOfGuitars(int size) throws JSONException {
        response = responseEntity.getBody();
        JSONArray array = new JSONArray(response);
        Assert.assertEquals(size, array.length());
    }


    @Given("User is Logged in")
    public void userIsLoggedIn() throws URISyntaxException, JsonProcessingException, JSONException {
        URI uri = new URI(baseUrl + "/Login");
        ObjectMapper mapper = new ObjectMapper();
        headers.setContentType(MediaType.APPLICATION_JSON);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("prinsalvino");
        userDTO.setPassword("test123");
        HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(userDTO), headers);
        responseEntity = template.postForEntity(uri, entity, String.class);
        token = new JSONObject(responseEntity.getBody()).getString("token");
        headers.add("Authorization", "Bearer " + token);

    }

    @Given("Employee is creating a new User")
    public void employeeIsCreatingANewUser() throws URISyntaxException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User("check1", "check123", "Check", "Check", "check@gmx.com", LocalDate.of(1993, 8, 02), "Rijswijk", "2282JV", "Rijswijk", "062535199", User.TypeEnum.CUSTOMER, true);
        URI uri = new URI(baseUrl + "/users");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(user), headers);
        responseEntity = template.postForEntity(uri, entity, String.class);
    }

    @Then("user should be created")
    public void userShouldBeCreated() {
    }

    //transaction steps


//    @When("I retrieve guitar with id {int}")
//    public void iRetrieveGuitarWithId(int id) throws URISyntaxException {
//        URI uri = new URI(baseUrl + "/" + id);
//        responseEntity = template.getForEntity(uri, String.class);
//    }
//
//    @Then("The guitar brand is {string}")
//    public void theGuitarBrandIs(String brand) throws JSONException {
//        response = responseEntity.getBody();
//        Assert.assertEquals(brand,
//                new JSONObject(response)
//                        .getString("brand"));
//    }
//
//    @When("I post a guitar")
//    public void iPostAGuitar() throws JsonProcessingException, URISyntaxException {
//        ObjectMapper mapper = new ObjectMapper();
//        Guitar guitar = new Guitar("Gibson", "Flying V", 2599);
//        URI uri = new URI(baseUrl);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(guitar), headers);
//        responseEntity = template.postForEntity(uri, entity, String.class);
//    }
}
