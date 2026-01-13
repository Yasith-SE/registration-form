package edu.icet.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString


public class signup {

    private String firstName;

    private String lastName;

    private String emailAddress;

    private String password;

    private String confirmPassword;

}
