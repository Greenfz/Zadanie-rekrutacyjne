package accountValidation.validationStrategy;

import model.Account;

import java.time.LocalDate;
import java.util.Date;

public class DateValidator implements ValidatorStrategy {

    @Override
    public boolean isValid(Account account) {
        LocalDate closingDate = account.getClosingDate();

        return closingDate.isAfter(LocalDate.now().minusDays(1));
    }
}
