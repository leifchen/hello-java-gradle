package com.chen.spring.manager;

import com.chen.spring.service.UserService;

/**
 * BookManager
 *
 * @Author LeifChen
 * @Date 2018-10-15
 */
public class UserManager {

    private final UserService userService;

    public UserManager(UserService userService) {
        this.userService = userService;
    }

    public void test() {
        userService.save();
    }
}
