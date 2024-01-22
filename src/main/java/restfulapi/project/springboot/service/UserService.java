package restfulapi.project.springboot.service;

import restfulapi.project.springboot.dto.UserDto;
import restfulapi.project.springboot.entity.User;

import java.util.List;

public interface UserService {
    UserDto CreateUser(UserDto user);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto user);
    void  deleteUser(Long userId);
}
