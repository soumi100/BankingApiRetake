package io.swagger.model;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.threeten.bp.LocalDate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user;
    public static final String PHONE_VERIFICATION = "^\\d{10}$";
    private static final String regexEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static Pattern pattern;
    private static Matcher matcher;

    @BeforeEach
    public void before() throws Exception {

        this.user = new User();
    }

    @Test
    public void createTransactionShouldNotBeNull() {
        assertNotNull(user);
    }

    @Test
    public void userNameShouldNotBeNull() {
        user.setUsername("prins");
        assertNotNull(user.getUsername());
    }
    @Test
    public void birthDateShouldBeLessThanToday(){
        LocalDate birthDate = LocalDate.of(2000, 8, 02);
        LocalDate today = LocalDate.now();
        user.setBirthdate(birthDate);
        Assert.assertTrue(today.compareTo(birthDate) > 0);
    }
    @Test
    public void phoneNumberShouldBe10Digit() {
        String phoneNumber = "0618913710";
        pattern = Pattern.compile(PHONE_VERIFICATION);
        matcher = pattern.matcher(phoneNumber);
        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void phoneNumberShouldNotContainAlphabet() {
        String phoneNumber = "P618913710";
        pattern = Pattern.compile(PHONE_VERIFICATION);
        matcher = pattern.matcher(phoneNumber);
        Assert.assertFalse(matcher.matches());
    }

    @Test
    public void emailShouldBeValid() throws Exception {
        String email = "prinsalvino@gmmx.com";
        pattern = Pattern.compile(regexEmail);
        matcher = pattern.matcher(email);
        assertTrue( matcher.matches());
    }
    @Test
    public void emailNotCompleteIsFalse() throws Exception {
        String email = "prinsalvinogmmx.com";
        pattern = Pattern.compile(regexEmail);
        matcher = pattern.matcher(email);
        assertFalse( matcher.matches());
    }
}