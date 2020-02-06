package accountValidation;

import accountValidation.validationStrategy.*;
import lombok.Setter;
import model.Account;

@Setter
public class AccountValidator {

    private ValidatorStrategy validatorStrategy;

    public boolean isAccountValid(Account account) {

        setValidatorStrategy(new BalanceValidator());
        if ( !validatorStrategy.isValid(account) ){
            return false;
        }

        setValidatorStrategy(new CurrencyValidator());
        if ( !validatorStrategy.isValid(account) ){
            return false;
        }

        setValidatorStrategy(new DateValidator());
        if ( !validatorStrategy.isValid(account) ){
            return false;
        }

        setValidatorStrategy(new IbanValidator());
        if ( !validatorStrategy.isValid(account) ){
            return false;
        }

        return true;
    }
}
