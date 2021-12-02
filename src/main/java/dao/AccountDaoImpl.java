package dao;

import models.Account;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {

    String url;
    String username;
    String password;

    static Logger logger = Logger.getLogger(AccountDaoImpl.class);

    public AccountDaoImpl() {
        this.url = "jdbc:postgresql://" + System.getenv("AWS_RDS_ENDPOINT") + "/jfbanking";
        this.username = System.getenv("AWS_RDS_USERNAME");
        this.password = System.getenv("AWS_RDS_PASSWORD");
    }

    public AccountDaoImpl(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<Account> getAllAccounts(Integer clientId) {


        List<Account> accounts = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "SELECT * FROM accounts WHERE clientid = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, clientId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                accounts.add(new Account(rs.getInt(1), rs.getDouble(2), clientId));
            }


        } catch (SQLException e) {
            logger.info(e);
        }
        return accounts;

    }

    @Override
    public List<Account> getAllAccounts(Integer clientId, Integer min, Integer max) {
        List<Account> accounts = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "SELECT * FROM accounts WHERE balance > ? AND balance < ? ;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, min);
            ps.setInt(2,max);
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                accounts.add(new Account(rs.getInt(1), rs.getDouble(2), clientId));
            }


        } catch (SQLException e) {
            logger.info(e);
        }
        return accounts;
    }

    @Override
    public Account getOneAccount(Integer accountId, Integer clientId) {
        Account account = new Account();

        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "SELECT * FROM accounts WHERE clientid = ? AND accountid = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, clientId);
            ps.setInt(2, accountId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                account = new Account(rs.getInt(1), rs.getDouble(2), rs.getInt(3));
            }
        } catch (SQLException e) {
            logger.info(e);
        }
        return account;
    }

    @Override
    public void createAccount(Account account) {

        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "INSERT INTO accounts VALUES (DEFAULT, ? , ?);";


            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, account.getBalance());
            ps.setInt(2, account.getClientId());


            ps.executeUpdate();


        } catch (SQLException e) {
            logger.info(e);

        }
    }

    @Override
    public void updateAccount(Account account) {

        try (Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "UPDATE accounts SET balance = ? WHERE accountid = ?;";


            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, account.getBalance());
            ps.setInt(2, account.getAccountId());

            ps.executeUpdate();


        }catch (SQLException e) {
            logger.info(e);
        }

    }

    @Override
    public void deleteAccount(Integer accountID) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "DELETE FROM accounts WHERE accountid = ? );";


            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, accountID);

            ps.executeUpdate();


        } catch (SQLException e) {
            logger.info(e);

        }

    }

}
