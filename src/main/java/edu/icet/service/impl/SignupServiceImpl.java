package edu.icet.service.impl;

import edu.icet.model.signup;
import edu.icet.repository.impl.signupRepositoryImpl;
import edu.icet.repository.signupRepository;
import edu.icet.service.signupService;

import java.sql.SQLException;

public class signupServiceImpl implements signupService {

    signupRepository signupRepository = new signupRepositoryImpl();

    @Override
    public void register(signup signupp) {
        try {
            signupRepository.register(signupp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
