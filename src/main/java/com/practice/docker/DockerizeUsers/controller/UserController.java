package com.practice.docker.DockerizeUsers.controller;

import com.practice.docker.DockerizeUsers.domain.User;
import com.practice.docker.DockerizeUsers.exception.UserNotFoundException;
import com.practice.docker.DockerizeUsers.service.SecurityTokenGenerator;
import com.practice.docker.DockerizeUsers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class UserController {

    private ResponseEntity responseEntity;

    private UserService userService;

    SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(UserService userService ,SecurityTokenGenerator securityTokenGenerator){
        this.userService=userService;
        this.securityTokenGenerator=securityTokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUer(@RequestBody User user){

        User user1=userService.addUser(user);
        responseEntity=new ResponseEntity<>(user1, HttpStatus.CREATED);
        return responseEntity;
    }
    @GetMapping("/userdata/v1/users")
    public ResponseEntity<?> getAllUser(){
        List<User> userList=userService.getAllUsers();
        responseEntity=new ResponseEntity<>(userList,HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody User user) throws UserNotFoundException {

        Map<String, String> map = null;
        try {
            User userObj = userService.findByUserNameAndUserPassword(user.getUserName(), user.getUserPassword());
            if (userObj.getUserName().equals(user.getUserName())) {
                map = securityTokenGenerator.generateToken(user);
            }
            responseEntity = new ResponseEntity(map, HttpStatus.OK);
        }
        catch(UserNotFoundException e){
            throw new UserNotFoundException();
        }
        catch (Exception e){
            responseEntity = new ResponseEntity("Try after sometime!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @DeleteMapping("/userdata/v1/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") int userId) throws UserNotFoundException{

        try{
            userService.deleteUser(userId);
            responseEntity=new ResponseEntity<>("Successfully Deleted 1 record",HttpStatus.OK);

        }catch (UserNotFoundException e){

            throw new UserNotFoundException();

        }catch (Exception e){
            responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

}
