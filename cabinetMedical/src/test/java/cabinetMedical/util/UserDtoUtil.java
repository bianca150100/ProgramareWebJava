package cabinetMedical.util;

import cabinetMedical.domain.UserType;
import cabinetMedical.dto.UserDto;
import org.apache.tomcat.jni.User;

public class UserDtoUtil {
    public static UserDto aUserDto(String username, String firstName, String lastName) {
        return UserDto.builder()
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .email("email@email.com")
                .password("parolauser")
                .build();
    }


}
