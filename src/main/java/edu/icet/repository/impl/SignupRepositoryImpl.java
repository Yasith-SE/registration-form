package edu.icet.repository.impl;

import edu.icet.DBConnector.DBConnection;
import edu.icet.model.signup;
import edu.icet.repository.signupRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class signupRepositoryImpl implements signupRepository {


    @Override
    public void register(signup signupp) throws SQLException {

        String SQL = "INSERT INTO signup (first_name, last_name, email_address, password) VALUES (?,?,?,?)";

        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);

        preparedStatement.setObject(1, signupp.getFirstName());
        preparedStatement.setObject(2, signupp.getLastName());
        preparedStatement.setObject(3, signupp.getEmailAddress());
        preparedStatement.setObject(4, signupp.getPassword());

        preparedStatement.executeUpdate();
    }
}
