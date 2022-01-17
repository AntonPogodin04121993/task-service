package com.task.taskservice.service;

import com.task.taskservice.dto.requestdto.RequestUserDto;
import com.task.taskservice.dto.responsedto.ResponseUserDto;

public interface UserService {

    void saveUser(RequestUserDto userDto);

    ResponseUserDto findByUsername(String username);
}
