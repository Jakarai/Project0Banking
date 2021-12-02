package services;

import dao.AccountDao;
import dao.AccountDaoImpl;
import models.Account;

import java.util.List;

public class AccountService {

    AccountDao accountDao;

    /// account services


    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public AccountService() {
        this.accountDao = new AccountDaoImpl();
    }

    public List<Account> getAllAccounts(Integer clientId) {
        return accountDao.getAllAccounts(clientId);

    }

    public List<Account> getAllAccounts(Integer clientId, Integer min, Integer max) {
        return accountDao.getAllAccounts(clientId, min, max);

    }
//    public List<Account> getAllAccounts() {
//        return accountDao.getAllAccounts();
//    }

    public Account getOneAccount(Integer clientId, Integer accountId) {
        return accountDao.getOneAccount(clientId,accountId);
    }

    public Boolean createAccount(Account account ) {
        if (account.getClientId() < 0) {
            return false;
        }
        accountDao.createAccount(account);
        return true;
    }

//    public boolean updateClient(Client client, Integer clientID) {
//
//
//
//    }

    public void deleteAccount(Integer clientId) {
        accountDao.deleteAccount(clientId);
    }


    public boolean updateAccount(Account account) {
        if (account.getAccountId() > 0 ){
            return false;
        }
        accountDao.updateAccount(account);
        return true;

    }

}
