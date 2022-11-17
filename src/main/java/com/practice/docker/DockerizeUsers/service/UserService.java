package com.practice.docker.DockerizeUsers.service;

import com.practice.docker.DockerizeUsers.domain.User;
import com.practice.docker.DockerizeUsers.exception.UserNotFoundException;

import java.util.List;

public interface UserService {

    User addUser(User user);
    List<User> getAllUsers();
    User findByUserNameAndUserPassword(String userName,String userPassword)throws UserNotFoundException;
    boolean deleteUser(int userId) throws UserNotFoundException;

}
