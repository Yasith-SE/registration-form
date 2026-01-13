package edu.icet.repository.impl;

import edu.icet.DBConnector.DBConnection;
import edu.icet.model.userLogin;
import edu.icet.repository.UserLoginRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLoginRepositoryImpl implements UserLoginRepository {

    @Override
    public void save(userLogin userLoginn) throws SQLException {
        String SQL = "INSERT INTO user_login (email, password) VALUES (?, ?)";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setObject(1, userLoginn.getEmail());
        preparedStatement.setObject(2, userLoginn.getPassword());
        preparedStatement.executeUpdate();
    }

    @Override
    public boolean validate(String email, String password) throws SQLException {
        String SQL = "SELECT count(1) FROM user_login WHERE email = ? AND password = ?";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setObject(1, email);
        preparedStatement.setObject(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt(1) == 1;
        }
        return false;
    }
}