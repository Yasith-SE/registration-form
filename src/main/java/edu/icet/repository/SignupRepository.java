package edu.icet.repository;

import edu.icet.model.signup;

import java.sql.SQLException;

public interface signupRepository {

    void register(signup signup) throws SQLException;

}
