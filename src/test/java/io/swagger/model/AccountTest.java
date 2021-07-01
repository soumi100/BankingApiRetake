package io.swagger.model;

import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.threeten.bp.LocalDate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    Account account;

    @BeforeEach
    public void before() throws Exception {
        User soumia =  new User(1L, "SB", "pass123",
                "soumia", "bouhouri", "sou@gmx.com",
                LocalDate.of(1993, 8, 02),
                "Rijswijk", "2282JV", "Rijswijk", "062535199",
                User.TypeEnum.EMPLOYEE, true);
        this.account = new Account(1l, Account.TypeEnum.CURRENT,
                Account.CurrencyEnum.EUR, true, "NL01INHO00000000010", 9989);
    }

    @Test
    public void createAccountShouldNotBeNull() {
        Account account1 = new Account();
        assertNotNull(account1);
    }

    @Test
    public void testSetBalance() throws Exception {
        //set the Balance
        account.setBalance(Integer.valueOf(7000));
        Integer expected = Integer.valueOf(7000);

        Integer actual = account.getBalance();
        assertEquals(expected, actual);
    }
    @Test
    public void testAccounIBAN() throws Exception {
        account.setIban("NL71INHO09631273");
        String expected = "NL71INHO09631273";
        String actual = account.getIban();
        assertEquals(expected, actual);
    }
    @Test
    public void testGetIBAN() throws Exception {
        String expected = "NL01INHO00000000010";
        String actual = account.getIban();
        // Test if equal
        assertEquals(expected, actual);
    }

    @Test
    public void testAccountType() throws Exception {
        account.setType(Account.TypeEnum.CURRENT);

        Account.TypeEnum expected = Account.TypeEnum.CURRENT;
        Account.TypeEnum actual = account.getType();

        // Test if equal
        assertEquals(expected, actual);
    }

    @Test
    public void testAccountCurrency() throws Exception {
        account.setCurrency(Account.CurrencyEnum.EUR);

        Account.CurrencyEnum expected = Account.CurrencyEnum.EUR;
        Account.CurrencyEnum actual = account.getCurrency();

        // Test if equal
        assertEquals(expected, actual);
    }
}