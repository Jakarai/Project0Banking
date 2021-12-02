package models;


public class Account {

    public Integer accountId;
    public Double balance;
    public Integer clientId;

    public Account() {

    }

    public Account(Account account, Integer accountId){
        this.accountId = accountId;
        this.balance = account.getBalance();
        this.clientId = account.getClientId();


    }


    public Account(Double balance, Integer clientId) {
        this.accountId = 0;
        this.balance = balance;
        this.clientId = clientId;
    }

    public Account(Integer accountId, Double balance, Integer clientId) {
        this.accountId = accountId;
        this.balance = balance;
        this.clientId = clientId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }


    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                ", clientId=" + clientId +
                '}';
    }
}
