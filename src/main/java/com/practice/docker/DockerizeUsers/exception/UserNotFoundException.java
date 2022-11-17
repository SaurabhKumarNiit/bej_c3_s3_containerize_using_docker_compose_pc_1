package com.practice.docker.DockerizeUsers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "User Not Found Please Check your Data")
public class UserNotFoundException extends Exception{
}
