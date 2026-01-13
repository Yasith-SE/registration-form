package edu.icet.repository;

import edu.icet.model.userLogin;
import java.sql.SQLException;

public interface UserLoginRepository {

    void save(userLogin user) throws SQLException;

    boolean validate(String email, String password) throws SQLException;
}