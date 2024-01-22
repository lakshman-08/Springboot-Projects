package restfulapi.project.springboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import restfulapi.project.springboot.dto.UserDto;
import restfulapi.project.springboot.entity.User;

@Mapper
public interface AutoUserMapper {

    AutoUserMapper MAPPER= Mappers.getMapper(AutoUserMapper.class);
    UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);
}
