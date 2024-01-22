package restfulapi.project.springboot.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import restfulapi.project.springboot.dto.UserDto;
import restfulapi.project.springboot.entity.User;
import restfulapi.project.springboot.exception.ErrorDetails;
import restfulapi.project.springboot.exception.ResourceNotFoundException;
import restfulapi.project.springboot.service.UserService;

import java.time.LocalDateTime;
import java.util.List;


@Tag(
        name = "CRUD REST APIs for User Resource",
        description = "CRUD REST APIs - Create User,Update User,Get User,Get All Users,Delete User"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class Usercontroller {

    private UserService userService;

    @Operation(
            summary = "Create USer REST API",
            description = "Create User REST API is used to save user in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    //build create user rest api
    @PostMapping
    public ResponseEntity<UserDto> CreateUser(@Valid @RequestBody UserDto user)
    {
        UserDto savedUser=userService.CreateUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get User By ID REST API",
            description = "Get User By ID REST API is used to get a single user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    //build get user by id REST API
    // http://localhost:8080/api/users/1
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId)
    {
        UserDto user=userService.getUserById(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @Operation(
            summary = "Get All Users REST API",
            description = "Get All Users REST API is used to get a all the users from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    //Build get all users REST API
    // http://localhost:8080/api/users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        List<UserDto> users=userService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @Operation(
            summary = "Update User REST API",
            description = "Update User REST API is used to update a particular user in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    //Build Update User REST API
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updatedUser(@PathVariable("id") Long userId,@RequestBody @Valid UserDto user)
    {
        user.setId(userId);
        UserDto updatedUser=userService.updateUser(user);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @Operation(
            summary = "Delete User REST API",
            description = "Delete User REST API is used to delete a particular user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    //build delete REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId)
    {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User succussfully deleted!",HttpStatus.OK);

    }


    /*@ExceptionHandler(ResourceNotFoundException.class) //Specific exception to handle custom error message back to client
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){

        ErrorDetails errorDetails=new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "USER_NOT_FOUND"
        );

        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }*/

}
