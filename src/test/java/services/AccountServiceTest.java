package services;

import dao.AccountDao;
import models.Account;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class AccountServiceTest {

    AccountDao accountDao = Mockito.mock(AccountDao.class);

    AccountService accountService;

    public AccountServiceTest() {
        this.accountService = new AccountService(accountDao);
    }

    @Test
    void accountService() {
    }

    @Test
    void accountServices() {
    }

    @Test
    void getAllAccounts() {

        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(1, 300.00, 3));
        accounts.add(new Account(2, 5000.00, 3));
        accounts.add(new Account(3, 2000.00, 2));
        List<Account> expectedValue = accounts;
        Mockito.when(accountDao.getAllAccounts(2)).thenReturn(accounts);

        List<Account> actualResult = accountService.getAllAccounts(2);

        assertEquals(expectedValue,actualResult);
    }

    @Test
    void getOneAccount() {
    }

    @Test
    void createAccount() {
    }

    @Test
    void deleteClient() {
    }
}