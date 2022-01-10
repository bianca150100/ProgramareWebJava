package cabinetMedical.util;

import cabinetMedical.domain.User;
import cabinetMedical.domain.UserType;

public class UserUtil {
    public static User aUser(String username, String firstName, String lastName) {
        return User.builder()
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .email("email@email.com")
                .password("parolauser")
                .userType(UserType.PACIENT)
                .build();
    }

    public static User aUser(long id) {
        return User.builder()
                .id(id)
                .username("username")
                .firstName("firstName")
                .lastName("lastName")
                .email("email@email.com")
                .password("parolauser")
                .userType(UserType.PACIENT)
                .build();
    }
}
