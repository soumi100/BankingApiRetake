package io.swagger.model;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {
    Transaction transaction;

    @BeforeEach
    public void before() throws Exception {
        this.transaction = new Transaction
                ("NL01INHO00000000010", "NL01INHO00000000080",
                        600d, "berlin dinner", 1L, Transaction.TransactionTypeEnum.TRANSFER);
    }

    @Test
    public void createTransactionShouldNotBeNull() {
        assertNotNull(transaction);
    }
    @Test
    public void invalidTransactionTypeShouldThrowIllegalArgumentException(){
        String TransactionTypeInput = "DEPOSIT";
        boolean isValid = false;
        for (Transaction.TransactionTypeEnum t : Transaction.TransactionTypeEnum.values()){
            if (t.name().equals(TransactionTypeInput)) {
                isValid = true;
            }
        }
        if (!isValid) throw new IllegalArgumentException("Transaction type is not valid!");
    }
    @Test
    public void transactionAmountShouldNotBeNull() {
        transaction.setAmount(1.0);
        assertNotNull(transaction.getAmount());
    }
    @Test
    public void transactionAmountShouldBeAboveZero(){
        transaction.setAmount(5.0);
        Assert.assertTrue(transaction.getAmount() > 0);
    }
    @Test
    public void transactionDescriptionShouldNotBeNull() {
        transaction.setDescription("doja cat concert");
        assertNotNull(transaction.getDescription());
    }

    @Test
    public void testFromIBAN() throws Exception {
        String expected = "NL01INHO00000000010";
        String actual = transaction.getAccountFrom();
        // Test if equal
        assertEquals(expected, actual);
    }

    @Test
    public void testToIBAN() throws Exception {
        String expected = "NL01INHO00000000080";
        String actual = transaction.getAccountTo();
        // Test if equal
        assertEquals(expected, actual);
    }
}