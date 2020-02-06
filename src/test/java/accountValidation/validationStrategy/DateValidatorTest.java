package accountValidation.validationStrategy;

import model.Account;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.*;

public class DateValidatorTest {

    DateValidator dateValidator = new DateValidator();
    Account account;

    @Before
    public void init() {
        this.account = new Account();
    }

    @Test
    public void oneDayToClose_shouldReturnTrue() {
        // GIVEN
        account.setClosingDate(LocalDate.now().plusDays(1));
        // WHEN
        boolean result = dateValidator.isValid(account);
        // THEN
        assertTrue(result);
    }

    @Test
    public void oneDayAfterClose_shouldReturnFalse() {
        // GIVEN
        account.setClosingDate(LocalDate.now().minusDays(1));
        // WHEN
        boolean result = dateValidator.isValid(account);
        // THEN
        assertFalse(result);
    }

    @Test
    public void exactlyOnClosingDay_imoShouldReturnTrue() {
        // GIVEN
        account.setClosingDate(LocalDate.now());
        // WHEN
        boolean result = dateValidator.isValid(account);
        // THEN
        assertTrue(result);
    }

    @Test
    public void expiredLongTimeAgo_shouldReturnFalse() {
        // GIVEN
        account.setClosingDate(LocalDate.parse("2010-01-01"));
        // WHEN
        boolean result = dateValidator.isValid(account);
        // THEN
        assertFalse(result);
    }

}