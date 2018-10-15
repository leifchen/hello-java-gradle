package com.chen.spring.dao.impl;

import com.chen.spring.dao.UserDao;

/**
 * BookDaoImpl
 *
 * @Author LeifChen
 * @Date 2018-10-15
 */
public class UserDaoImpl implements UserDao {

    @Override
    public void save() {
        System.out.println("Spring BookDao method: save().");
    }
}
