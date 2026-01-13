package edu.icet.service.impl;

import edu.icet.model.userLogin;
import edu.icet.repository.UserLoginRepository;
import edu.icet.repository.impl.UserLoginRepositoryImpl;
import edu.icet.service.UserLoginService;

import java.sql.SQLException;

public class UserLoginServiceImpl implements UserLoginService {

    UserLoginRepository userLoginRepository = new UserLoginRepositoryImpl();

    @Override
    public void saveUserLogin(userLogin userLoginn) {
        try {
            userLoginRepository.save(userLoginn);
        } catch (SQLException e) {
            throw new RuntimeException("Error saving login details", e);
        }
    }

    @Override
    public boolean authenticateUser(String email, String password) {
        try {
            return userLoginRepository.validate(email, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}