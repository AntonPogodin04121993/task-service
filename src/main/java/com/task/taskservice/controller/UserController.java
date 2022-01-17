package com.task.taskservice.controller;

import com.task.taskservice.dto.requestdto.RequestUserDto;
import com.task.taskservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Valid RequestUserDto userDto) {
        log.info("Get request to register the user");
        userService.saveUser(userDto);
    }

}
