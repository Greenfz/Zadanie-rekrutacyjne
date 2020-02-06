package accountValidation;

import accountValidation.validationStrategy.BalanceValidator;
import accountValidation.validationStrategy.ValidatorStrategy;
import model.Account;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class AccountValidatorTest {

    ValidatorStrategy validatorStrategy = mock(BalanceValidator.class);
    @InjectMocks
    AccountValidator accountValidator = spy(AccountValidator.class);


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        doNothing()
                .when(accountValidator)
                .setValidatorStrategy(any(ValidatorStrategy.class));
    }

    @Test
    public void validatorStrategyReturnFalse_shouldReturnFalseAfterOneCall() {

            when
                (validatorStrategy.isValid(any(Account.class)))
                .thenReturn(false);

        boolean result = accountValidator.isAccountValid(new Account());
        // THEN
        assertFalse(result);
        verify(
                validatorStrategy, Mockito.times(1))
                .isValid(Mockito.any(Account.class));
    }

    @Test
    public void validatorStrategyReturnTrue_shouldReturnTrueAfterFourCalls() {

            when(
                validatorStrategy.isValid(any(Account.class)))
                .thenReturn(true);

        boolean result = accountValidator.isAccountValid(new Account());

        // THEN
        assertTrue(result);
        verify(
                validatorStrategy, Mockito.times(4))
                .isValid(Mockito.any(Account.class));
    }

}