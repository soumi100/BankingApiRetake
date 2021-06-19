package io.swagger;

import io.swagger.model.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class AccountTest {
    @Test
    public void createAccountShouldNotBeNull(){
        Account account1 = new Account();
        assertNotNull(account1);
    }

}