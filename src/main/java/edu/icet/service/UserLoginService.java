package edu.icet.service;

import edu.icet.model.userLogin;

public interface UserLoginService {
    void saveUserLogin(userLogin userLoginn);
    boolean authenticateUser(String email, String password);
}