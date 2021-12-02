package dao;

import models.Client;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl implements ClientDao {
    String url;
    String username;
    String password;

    static Logger logger = Logger.getLogger(ClientDaoImpl.class);



    public ClientDaoImpl() {
        this.url = "jdbc:postgresql://" + System.getenv("AWS_RDS_ENDPOINT") + "/jfbanking";
        this.username = System.getenv("AWS_RDS_USERNAME");
        this.password = System.getenv("AWS_RDS_PASSWORD");
    }

    public ClientDaoImpl(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<Client> getAllClients() {


        List<Client> clients = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "SELECT * FROM clients;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
//            System.out.println(rs);

            while(rs.next()) {
                clients.add(new Client(rs.getInt(1), rs.getString(2)));
//                System.out.println(clients);
            }


        }catch (SQLException e) {
           logger.info(e);
        }
//        System.out.println(clients + "clients");
        return clients;
    }

    @Override
    public Client getOneClient(Integer clientID) {
        Client client = null;

        try (Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "SELECT * FROM clients WHERE clientid = ?;";


            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, clientID);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                client = new Client(rs.getInt(1), rs.getString(2));
            }

        }catch (SQLException e) {
            logger.info(e);
        }
        return client;
    }


    @Override
    public void createClient(Client client) {
        try (Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "INSERT INTO clients VALUES (DEFAULT, ? );";


            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, client.getUsername());

            ps.executeUpdate();


        }catch (SQLException e) {
            logger.info(e);
        }
    }

    @Override
    public void updateClient(Client client, Integer clientId) {
        try (Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "UPDATE clients SET username = ? WHERE clientid = ?;";


            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, client.getUsername());
            ps.setInt(2, clientId);

            ps.executeUpdate();


        }catch (SQLException e) {
            logger.info(e);
        }

    }

    @Override
    public void deleteClient(Integer clientID) {

        try (Connection conn = DriverManager.getConnection(url, username, password)){

            String sql = "DELETE FROM clients WHERE clientid = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, clientID);

            ps.executeUpdate();

        }catch (SQLException e) {
            logger.info(e);
        }
    }


}

