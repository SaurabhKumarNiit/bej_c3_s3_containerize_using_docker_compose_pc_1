package com.practice.docker.DockerizeUsers.repository;

import com.practice.docker.DockerizeUsers.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserNameAndUserPassword(String userName,String userPassword);
}
