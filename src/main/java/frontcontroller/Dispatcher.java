package frontcontroller;

import controllers.AccountController;
import controllers.ClientController;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;


public class Dispatcher {

//    BankController bankController = new BankController();
    AccountController accountController = new AccountController();
    ClientController clientController = new ClientController();

    public Dispatcher(Javalin app) {

    app.routes(() -> {
        path("/clients", () -> {
            get(clientController::getAllClients);
            post(clientController::createClient);
            path("/{id}", () -> {
                get(clientController::getOneClient);
                put(clientController::updateClient);
                patch(clientController::updateClient);
                delete(clientController::deleteClient);
                path("/accounts", () -> {
                    get(accountController::getAllAccounts);
                    post(accountController::createAccount);
                    path("/{accountid}", () -> {
                    get(accountController::getOneAccount);
                    put(accountController::updateAccount);
                    patch(accountController::updateAccount);
                    delete(accountController::deleteAccount);
                    });

                });
            });
        });

    });

//    app.routes(() -> {
//        path("/accounts", () ->{
//            get(accountController::getAllAccounts);
//        });
//    });

    }
}
