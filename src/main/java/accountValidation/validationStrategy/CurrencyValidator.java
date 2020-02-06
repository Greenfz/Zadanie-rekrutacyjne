package accountValidation.validationStrategy;

import model.Account;

public class CurrencyValidator implements ValidatorStrategy {

    @Override
    public boolean isValid(Account account) {

        String currency = account.getCurrency();

        return "PLN".equals(currency);                 // zakładam że iban oraz skrót waluty zawierający małe litery jest błędny
    }
}
