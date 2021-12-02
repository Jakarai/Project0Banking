package dao;

import models.Client;

import java.util.List;

public interface ClientDao {

    List<Client> getAllClients();
    Client getOneClient(Integer clientId);
    void createClient(Client client);
//    void updateClient(Client client);
    void updateClient(Client client, Integer clientId);
    void deleteClient(Integer clientId);
}
