package com.practice.docker.DockerizeUsers.service;

import com.practice.docker.DockerizeUsers.domain.User;
import com.practice.docker.DockerizeUsers.exception.UserNotFoundException;
import com.practice.docker.DockerizeUsers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl (UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findByUserNameAndUserPassword(String userName, String userPassword) throws UserNotFoundException {
        User user=userRepository.findByUserNameAndUserPassword(userName,userPassword);
        if(user==null){
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public boolean deleteUser(int userId) throws UserNotFoundException {
        boolean result=false;
        if(userRepository.findById(userId).isEmpty()){

            throw new UserNotFoundException();

        }else {userRepository.deleteById(userId);result= true;}

        return result;
    }

    }
