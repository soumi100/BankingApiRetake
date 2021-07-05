package io.swagger.model;

import org.junit.Assert;
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
        this.account = new Account(1l, Account.TypeEnum.CURRENT,
                Account.CurrencyEnum.EUR, true, "NL01INHO00000000010", 9989);
    }

    @Test
    public void createAccountShouldNotBeNull() {
        assertNotNull(account);
    }
    @Test
    public void invalidCurrencyShouldThrowIllegalArgumentException(){
        String currencyInput = "EUR";
        boolean isValid = false;
        for (Account.CurrencyEnum c : Account.CurrencyEnum.values()){
            if (c.name().equals(currencyInput)) {
                isValid = true;
            }
        }
        if (!isValid) throw new IllegalArgumentException("Currency is not valid!");
    }
    @Test
    public void accountBalanceShouldBeAboveZero(){
        account.setBalance(5);
        Assert.assertTrue(account.getBalance() > 0);
    }
    @Test
    public void newAccountShouldBeActive(){
        Assert.assertTrue(account.isActive());
    }
    @Test
    public void invalidTypeShouldThrowIllegalArgumentException(){
        String accountType = "SAVINGS";
        boolean isValid = false;
        for (Account.TypeEnum c : Account.TypeEnum.values()){
            if (c.name().equals(accountType)) {
                isValid = true;
            }
        }
        if (!isValid) throw new IllegalArgumentException("Type is not valid!");
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