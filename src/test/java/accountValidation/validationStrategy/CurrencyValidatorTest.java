package accountValidation.validationStrategy;

import model.Account;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CurrencyValidatorTest {

    CurrencyValidator currencyValidator = new CurrencyValidator();
    Account account;

    @Before
    public void init() {
        this.account = new Account();
    }

    @Test
    public void lowerCasePLN_shouldReturnFalse() {
        // GIVEN
        account.setCurrency("pln");
        // WHEN
        boolean result = currencyValidator.isValid(account);
        // THEN
        assertFalse(result);
    }

    @Test
    public void anotherCurrency_shouldReturnFalse() {
        // GIVEN
        account.setCurrency("EUR");
        // WHEN
        boolean result = currencyValidator.isValid(account);
        // THEN
        assertFalse(result);
    }

    @Test
    public void upperCasePLN_shouldReturnTrue() {
        // GIVEN
        account.setCurrency("PLN");
        // WHEN
        boolean result = currencyValidator.isValid(account);
        // THEN
        assertTrue(result);
    }
}