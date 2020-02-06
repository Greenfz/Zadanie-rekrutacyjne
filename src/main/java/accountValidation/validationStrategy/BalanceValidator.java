package accountValidation.validationStrategy;

import model.Account;

public class BalanceValidator implements ValidatorStrategy {

    @Override
    public boolean isValid(Account account) {

        double balance = account.getBalance();

        return balance >= 0;
    }
}
