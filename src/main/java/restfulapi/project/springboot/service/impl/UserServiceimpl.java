package restfulapi.project.springboot.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import restfulapi.project.springboot.dto.UserDto;
import restfulapi.project.springboot.entity.User;
import restfulapi.project.springboot.exception.EmailAlreadyExistsException;
import restfulapi.project.springboot.exception.ResourceNotFoundException;
import restfulapi.project.springboot.mapper.AutoUserMapper;
import restfulapi.project.springboot.mapper.UserMapper;
import restfulapi.project.springboot.repository.UserRepository;
import restfulapi.project.springboot.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserServiceimpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto CreateUser(UserDto userDto) {

        //convert userdto into User JPA Entitty
        //User user= UserMapper.mapToUser(userDto);

        Optional<User> optionalUser=userRepository.findByEmail(userDto.getEmail());

        if(optionalUser.isPresent())
        {
            throw new EmailAlreadyExistsException("Email Already exists for a user");
        }

        //ModelMapper Implementation User user= modelMapper.map(userDto,User.class);

        //Map Struck Implementation
        User user= AutoUserMapper.MAPPER.mapToUser(userDto);

       User savedUser= userRepository.save(user);

       //convert User JPA enitty to UserDto
        //manual class creating implementation UserDto savedUserDto=UserMapper.mapToUserDto(savedUser);

       //Model Mapping Implementation UserDto savedUserDto=modelMapper.map(savedUser,UserDto.class);

        //Map Struck implementation
        UserDto savedUserDto=AutoUserMapper.MAPPER.mapToUserDto(savedUser);
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("User","id",userId)
        );



        //Manual Class Creation Implemention return UserMapper.mapToUserDto(user);

        //Model Map Implmentation return modelMapper.map(user,UserDto.class);

        //Map Struck Implmentation
        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
       List<User> users=userRepository.findAll();

       //Manual class Implementation
       //return users.stream().map(UserMapper::mapToUserDto)
             //   .collect(Collectors.toList());

        //Model Mapping Implmentation
       /* return users.stream().map((user)->modelMapper.map(user,UserDto.class))
                .collect(Collectors.toList());*/

        //Map Struck Implementation
        return users.stream().map((user)->AutoUserMapper.MAPPER.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser=userRepository.findById(user.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("User","id", user.getId())
        );


        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser=userRepository.save(existingUser);
        //Manual Class Implmentation return UserMapper.mapToUserDto(updatedUser);

       //Model Mapping Implementation return modelMapper.map(updatedUser,UserDto.class);

        //Map Struck Implementation
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {

        User existingUser=userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("User","id", userId)
        );

        userRepository.deleteById(userId);
    }
}
