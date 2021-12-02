package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import models.Client;
import services.ClientService;

import java.util.List;

public class ClientController {

    static ClientService clientService = new ClientService();

    public void getAllClients(Context context) throws JsonProcessingException {
        context.contentType("application/json");

        List<Client> clientList = clientService.getAllClients();

        String jsonString = new ObjectMapper().writeValueAsString(clientList);

        context.result(jsonString);

    }
    public void getOneClient(Context context) throws JsonProcessingException {
        context.contentType("application/json");

        Integer clientId = Integer.parseInt(context.pathParam("id"));

        Client client = clientService.getOneClient(clientId);

        String jsonString = new ObjectMapper().writeValueAsString(client);
        context.result(jsonString);
        System.out.println(client);



    }
    public void createClient(Context context) {
        Client client = context.bodyAsClass(Client.class);

        if (clientService.createClient(client)) {
            context.result("Client created.");
            System.out.println(client);
        }else {
            context.result("Sorry, please use a shorter username.");
        }



    }
    public void updateClient(Context context) {
        Integer clientId = Integer.parseInt(context.pathParam("id"));
        Client client = context.bodyAsClass(Client.class);

        clientService.updateClient(client, clientId);

        context.result("Client id: " + clientId + "has been updated");


    }
    public void deleteClient(Context context)  {
        Integer clientId = Integer.parseInt(context.pathParam("id"));

        clientService.deleteClient(clientId);

        context.result("Client with ID: "+ clientId+" has been deleted" );

    }

}
