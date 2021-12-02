package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import models.Account;
import services.AccountService;

import java.util.List;

public class AccountController {

    static AccountService accountService = new AccountService();


    public void getAllAccounts(Context context) throws JsonProcessingException {
        context.contentType("application/json");

        Integer clientId = Integer.parseInt(context.pathParam("id"));
        if (context.queryParam("amountGreaterThan") != null) {
        Integer min = Integer.parseInt(context.queryParam("amountGreaterThan"));
        Integer max = Integer.parseInt(context.queryParam("amountLessThan"));



        List<Account> accountList = accountService.getAllAccounts(clientId, min, max);

            String jsonString = new ObjectMapper().writeValueAsString(accountList);
            context.result(jsonString);


        }





        List<Account> accountList = accountService.getAllAccounts(clientId);

        String jsonString = new ObjectMapper().writeValueAsString(accountList);

        context.result(jsonString);
        System.out.println(accountList);
        System.out.println(jsonString);
        System.out.println(clientId);

    }
    public void getOneAccount(Context context) throws JsonProcessingException {

        context.contentType("application/json");

        Integer clientId = Integer.parseInt(context.pathParam("id"));
        Integer accountId = Integer.parseInt(context.pathParam("accountid"));

        Account account = accountService.getOneAccount(clientId, accountId );

        String jsonString = new ObjectMapper().writeValueAsString(account);
        context.result(jsonString);

    }
    public void createAccount(Context context)  {
        Account account = context.bodyAsClass(Account.class);

        if (accountService.createAccount(account)) {

            context.result("Client created.");

        } else {
            context.result("sorry account creation was UnSuccessful");
        }


    }
    public void updateAccount(Context context) throws JsonProcessingException {

        Integer accountId = Integer.parseInt(context.pathParam("accountid"));
        Account account = context.bodyAsClass(Account.class);

        if (accountService.updateAccount(account)) {
            context.result("Account with ID: " + accountId + " has been updated");
        } else {
            context.result("Sorry didnt work.");
        }


    }
    public void deleteAccount(Context context) throws JsonProcessingException {

        Integer accountId = Integer.parseInt(context.pathParam("accountid"));

        accountService.deleteAccount(accountId);

        context.result("Account with ID: " + accountId + " has been deleted.");

    }
}
