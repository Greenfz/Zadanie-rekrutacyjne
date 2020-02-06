package accountValidation.validationStrategy;

import model.Account;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IbanValidatorTest {

    IbanValidator ibanValidator = new IbanValidator();
    Account account;

    @Before
    public void init() {
        this.account = new Account();
    }

    @Test
    public void tooShort_shouldReturnFalse() {
        // GIVEN
        account.setIban("PL1212");
        System.out.println();
        // WHEN
        boolean result = ibanValidator.isValid(account);
        // THEN
        assertFalse(result);
    }

    @Test
    public void codeContainsLetters_shouldReturnFalse() {
        // GIVEN
        account.setIban("PL3FFF7891113151719212325228");
        System.out.println();
        // WHEN
        boolean result = ibanValidator.isValid(account);
        // THEN
        assertFalse(result);
    }

    @Test
    public void tooLong_shouldReturnFalse() {
        // GIVEN
        account.setIban("PL3456789111315171921232527293032");
        // WHEN
        boolean result = ibanValidator.isValid(account);
        // THEN
        assertFalse(result);
        assertNotEquals(account.getIban().length(), 28);
    }

    @Test
    public void lowerCasePL_shouldReturnFalse() {
        // GIVEN
        account.setIban("pl34567891113151719212325228");
        // WHEN
        boolean result = ibanValidator.isValid(account);
        // THEN
        assertFalse(result);
        assertEquals(account.getIban().length(), 28);
    }

    @Test
    public void anotherCountry_shouldReturnFalse() {
        // GIVEN
        account.setIban("VG34567891113151719212325228");
        // WHEN
        boolean result = ibanValidator.isValid(account);
        // THEN
        assertFalse(result);
        assertEquals(account.getIban().length(), 28);
    }

    @Test
    public void upperCasePLand28Length_shouldReturnTrue() {
        // GIVEN
        account.setIban("PL34567891113151719212325228");
        // WHEN
        boolean result = ibanValidator.isValid(account);
        // THEN
        assertTrue(result);
        assertEquals(account.getIban().length(), 28);
    }
}