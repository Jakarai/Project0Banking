package dao;

import models.Account;

import java.util.List;

public interface AccountDao {
    List<Account> getAllAccounts(Integer clientId);
    List<Account> getAllAccounts(Integer clientId, Integer min, Integer max);
    Account getOneAccount(Integer clientId, Integer accountId);
    void createAccount(Account account);
    void updateAccount(Account account);
    void deleteAccount(Integer accountId);


}
