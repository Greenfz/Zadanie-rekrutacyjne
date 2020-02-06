package accountsProcessing;

import accountValidation.AccountValidator;
import model.Account;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AccountsProcessorTest {

    AccountValidator accountValidator = mock(AccountValidator.class);
    @InjectMocks
    AccountsProcessor accountsFacade = new AccountsProcessor();


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void listOfValidAccounts_shouldReturnSorted() {
        //  GIVEN
        ArrayList<Account> list = prepareAccountsList();

        //  WHEN
        when(
                accountValidator.isAccountValid(any(Account.class)))
                .thenReturn(true);

        Set<Account> resultSet = accountsFacade.validateAndSortAccounts(list);
        Account[] accounts = parseSetToArray(resultSet);

        //  THEN
        assertEquals(list.size(), resultSet.size());
        assertEquals("A", accounts[0].getName());
        assertEquals("Z", accounts[2].getName());
    }

    @Test
    public void listOfInvalidAccounts_shouldReturnEmptySet() {
        //  GIVEN
        ArrayList<Account> list = prepareAccountsList();

        //  WHEN
        when(
                accountValidator.isAccountValid(any(Account.class)))
                .thenReturn(false);
        Set<Account> resultSet = accountsFacade.validateAndSortAccounts(list);

        //  THEN
        assertTrue(resultSet.isEmpty());
    }


    private ArrayList<Account> prepareAccountsList() {
        Account account1 = new Account();
        Account account2 = new Account();
        Account account3 = new Account();
        account1.setName("Z");
        account2.setName("A");
        account3.setName("B");

        ArrayList<Account> list = new ArrayList<>();
        list.add(account1);
        list.add(account2);
        list.add(account3);
        return list;
    }

    private Account[] parseSetToArray(Set<Account> resultSet) {
        int setSize = resultSet.size();
        Account[] accounts = new Account[setSize];
        resultSet.toArray(accounts);
        return accounts;
    }

}