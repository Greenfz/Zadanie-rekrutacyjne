package accountValidation.validationStrategy;

import model.Account;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BalanceValidatorTest {

    BalanceValidator balanceValidator = new BalanceValidator();

    private Account account;

    @Before
    public void init() {
        this.account = new Account();
    }

    @Test
    public void positiveAccountBalance_shouldReturnTrue() {
        // GIVEN
        account.setBalance(0.1);
        // WHEN
        boolean result = balanceValidator.isValid(account);
        // THEN
        assertTrue(result);
    }

    @Test
    public void positiveBigAccountBalance_shouldReturnTrue() {
        // GIVEN
        account.setBalance(98745613);
        // WHEN
        boolean result = balanceValidator.isValid(account);
        // THEN
        assertTrue(result);
    }

    @Test
    public void zeroAccountBalance_shouldReturnTrue() {
        // GIVEN
        account.setBalance(0.0);
        // WHEN
        boolean result = balanceValidator.isValid(account);
        // THEN
        assertTrue(result);
    }

    @Test
    public void negativeAccountBalance_shouldReturnFalse() {
        // GIVEN
        account.setBalance(-0.1);
        // WHEN
        boolean result = balanceValidator.isValid(account);
        // THEN
        assertFalse(result);
    }

    @Test
    public void negativeBigAccountBalance_shouldReturnFalse() {
        // GIVEN
        account.setBalance(-123456789);
        // WHEN
        boolean result = balanceValidator.isValid(account);
        // THEN
        assertFalse(result);
    }
}