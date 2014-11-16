package com.memoquest.service.entity;

import com.memoquest.dao.UserDao;
import com.memoquest.dao.rest.UserRestDao;
import com.memoquest.model.db.User;

import java.util.Date;
import java.util.List;

/**
 * Created by franck on 30/10/2014.
 */
public class UserService {

    private UserDao userDao;
    private UserRestDao userRestDao;

    public UserService() {
        userDao = new UserDao();
        userRestDao = new UserRestDao();
    }

    public User isAuthentifiateByServeur(String login, String password) {
        return userRestDao.isAuthentifiateByServeur(login, password);
    }

    public void edit(User user, Long userId) {

        if (user.getCreateTime() == null) {
            user.setCreateTime(new Date());
        }
        if (user.getCreateUser() == null) {
            user.setCreateUser(userId);
        }

        user.setUpdateTime(new Date());
        user.setUpdateUser(userId);

        user.save();
    }

    public User getUserActive() {
        return userDao.getUserActive();
    }

    public List<User> getAll() {
        return userDao.getAll();
    }


    public void setUserToActive(User user) {
        updateAllUserToNoActif();
        user.setActive(1);
        edit(user, (long) -1);
    }

    public void updateAllUserToNoActif() {
        for(User user : getAll()){
            user.setActive(0);
            edit(user, (long) -1);
        }
    }
}