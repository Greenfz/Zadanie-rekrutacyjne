package accountValidation.validationStrategy;

import model.Account;

public interface ValidatorStrategy {

    boolean isValid(Account account);
}
