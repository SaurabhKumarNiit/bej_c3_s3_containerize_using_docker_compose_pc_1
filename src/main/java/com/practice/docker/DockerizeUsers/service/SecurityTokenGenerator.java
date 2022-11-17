package com.practice.docker.DockerizeUsers.service;

import com.practice.docker.DockerizeUsers.domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    Map<String,String> generateToken(User user);
}
