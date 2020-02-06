package accountsProcessing;

import accountValidation.AccountValidator;
import lombok.AllArgsConstructor;
import lombok.Setter;
import model.Account;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Setter
@AllArgsConstructor
public class AccountsProcessor {

    AccountValidator accountValidator;

    public Set<Account> validateAndSortAccounts(List<Account> accountsList) {
        Set<Account> sortedAccountsSet = new TreeSet<>();

        for (Account account : accountsList) {
            if (validateAccount(account)) {
                sortedAccountsSet.add(account);
            }
        }
        return sortedAccountsSet;
    }

    private boolean validateAccount(Account account) {
        return accountValidator.isAccountValid(account);
    }

    public AccountsProcessor() {
        accountValidator = new AccountValidator();
    }
}
