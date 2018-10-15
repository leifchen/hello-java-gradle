package com.chen.spring.service.impl;

import com.chen.spring.dao.UserDao;
import com.chen.spring.service.UserService;

/**
 * BookServiceImpl
 *
 * @Author LeifChen
 * @Date 2018-10-15
 */
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save() {
        System.out.println("Spring BookService method: save().");
        userDao.save();
    }
}
