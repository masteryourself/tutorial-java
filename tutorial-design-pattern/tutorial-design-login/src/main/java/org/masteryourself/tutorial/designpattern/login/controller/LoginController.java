package org.masteryourself.tutorial.designpattern.login.controller;


import lombok.extern.slf4j.Slf4j;
import org.masteryourself.tutorial.designpattern.login.dto.LoginReq;
import org.masteryourself.tutorial.designpattern.login.dto.LoginResp;
import org.masteryourself.tutorial.designpattern.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginResp login(@RequestBody LoginReq loginReq) throws InterruptedException {
        return userService.login(loginReq);
    }
}
