import client.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    private static final String GET_CLIENT_BY_ID = "SELECT * FROM client WHERE ID = ?";
    private static final String GET_CLIENT_LIST = "SELECT * FROM client";
    private static final String INSERT_CLIENT_BY_NAME = "INSERT INTO client(NAME) VALUES (?)";
    private static final String UPDATE_NAME_BY_ID = "UPDATE client SET NAME = ? WHERE ID = ?";
    private static final String DELETE_CLIENT_BY_ID = "DELETE FROM client WHERE ID = ?";


    public static int insertClientByName(String name) throws SQLException, InsertExeption {
        if (name.length() < 2) {
            throw new InsertExeption("Error by insert");
        }
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT_BY_NAME, PreparedStatement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            int resultSetInt = resultSet.getInt(1);
            return resultSetInt;
        }
    }

    public static String getNameClientById(int id) throws SQLException {
        String name = null;
        try(Connection connection = Database.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CLIENT_BY_ID);)
        {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                name = resultSet.getString("NAME");
            }
            return name;
        }

    }

    public static void setNameClientById(int id, String name) throws SQLException, InsertExeption {
        try(Connection connection = Database.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NAME_BY_ID);)
        {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            int i = preparedStatement.executeUpdate();
            if(i == 0){
                throw  new InsertExeption("Id client is not exist");
            }
        }
    }

    public static void deleteClientById(int id) throws SQLException, InsertExeption {
        try(Connection connection = Database.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT_BY_ID);) {
            preparedStatement.setInt(1, id);
             int i = preparedStatement.executeUpdate();
            if(i == 0){
                throw  new InsertExeption("Id client is not exist");
            }
        }

    }

    public static List<Client> getClientList() throws SQLException {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_CLIENT_LIST)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                clients.add(new Client(resultSet.getInt("ID"), resultSet.getString("NAME")));
            }
            return clients;
        }

    }
}
