package cabinetMedical.mapper;

import cabinetMedical.domain.User;
import cabinetMedical.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto mapToDto(User user);

    User mapToEntity(UserDto user);
}

