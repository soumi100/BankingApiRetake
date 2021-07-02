package hellocucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;


class account {
    static String getIban() {
        return "NL01INHO00000000010";
    }
}

public class Stepdefs {
    private String actualIban;
    private String url;

    @Given("account iban is set in the url")
    public void account_iban_is_set_in_the_url() {
        actualIban = "NL01INHO00000000010"
    }
    @When("visiting accounts endpoint")
    public void visiting_accounts_endpoint() {
        url = "http://localhost:8484/accounts/NL71INHO09631273"
    }
    @Then("display the account matching the given iban")
    public void display_the_account_matching_the_given_iban() {
        assertEquals(account.getIban(), actualIban)
    }
}