package com.TCP.messaging.controller;

import com.TCP.messaging.dto.UserDataDTO;
import com.TCP.messaging.dto.UserResponseDTO;
import com.TCP.messaging.model.User;
import com.TCP.messaging.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class UserController {

    private ModelMapper modelMapper;
    private UserService userService;

    @Autowired
    public UserController(ModelMapper modelMapper, UserService userService){
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @PostMapping ("/signin")
    @ResponseStatus(HttpStatus.CREATED)
    public String login(@RequestParam String username, @RequestParam String password) {
        return null;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public String signup(@RequestBody UserDataDTO user) {
        return userService.signup(modelMapper.map(user, User.class));
    }

    @GetMapping(value = "/me")
    @CrossOrigin
    public UserResponseDTO whoami(HttpServletRequest req) {
        return modelMapper.map(userService.whoami(req), UserResponseDTO.class);
    }
}
