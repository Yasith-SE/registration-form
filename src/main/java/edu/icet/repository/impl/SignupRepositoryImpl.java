package edu.icet.repository.impl;

import edu.icet.DBConnector.DBConnection;
import edu.icet.model.signup;
import edu.icet.repository.signupRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class signupRepositoryImpl implements signupRepository {

    @Override
    public void register(signup signupp) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        connection.setAutoCommit(false);

        try {
            String sqlSignup = "INSERT INTO signup (first_name, last_name, email_address, password) VALUES (?,?,?,?)";
            try (PreparedStatement ps1 = connection.prepareStatement(sqlSignup)) {
                ps1.setString(1, signupp.getFirstName());
                ps1.setString(2, signupp.getLastName());
                ps1.setString(3, signupp.getEmailAddress());
                ps1.setString(4, signupp.getPassword());
                ps1.executeUpdate();
            }

            String sqlLogin = "INSERT INTO user_login (email, password) VALUES (?,?)";
            try (PreparedStatement ps2 = connection.prepareStatement(sqlLogin)) {
                ps2.setString(1, signupp.getEmailAddress());
                ps2.setString(2, signupp.getPassword());
                ps2.executeUpdate();
            }

            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}