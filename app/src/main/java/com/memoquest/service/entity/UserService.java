package com.memoquest.service.entity;

import com.memoquest.dao.UserDao;
import com.memoquest.model.db.User;

/**
 * Created by franck on 30/10/2014.
 */
public class UserService {

    private UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    public User getUserActive() {


        return userDao.getUserActive();
    }
}
