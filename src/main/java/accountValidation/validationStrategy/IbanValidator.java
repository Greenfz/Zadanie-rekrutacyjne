package accountValidation.validationStrategy;

import model.Account;

import java.util.regex.Pattern;

public class IbanValidator implements ValidatorStrategy {

    private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    @Override
    public boolean isValid(Account account) {

        String iban = account.getIban();
        String ibanNumberCode = getIbanNumberCose(iban);

        return (iban.startsWith("PL") && doesCodeContainsOnlyDigit(ibanNumberCode) && iban.length() == 28);
    }

    private String getIbanNumberCose(String iban) {
        return iban.substring(2);
    }

    private boolean doesCodeContainsOnlyDigit(String ibanNumberCode) {
        return pattern.matcher(ibanNumberCode).matches();
    }
}
