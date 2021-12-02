package services;

import dao.ClientDao;
import dao.ClientDaoImpl;
import models.Client;

import java.util.List;

public class ClientService {

    ClientDao clientDao;


    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public ClientService() {
        this.clientDao = new ClientDaoImpl();
    }

    public List<Client> getAllClients() {
        return clientDao.getAllClients();
    }

    public Client getOneClient(Integer clientId){
        return clientDao.getOneClient(clientId);
    }

    public Boolean createClient(Client client) {
        if (client.getUsername().length() > 30) {
            return false;
        }
        clientDao.createClient(client);
        return true;
    }

//    public void updateClient(Client client) {
//        clientDao.updateClient(client);
//    }

    public boolean updateClient(Client client, Integer clientID) {

       if (client.getUsername().length() > 30 ){
           return false;
       }
        clientDao.updateClient(client, clientID);
       return true;

    }

    public void deleteClient(Integer clientId) {
        clientDao.deleteClient(clientId);
    }



}
