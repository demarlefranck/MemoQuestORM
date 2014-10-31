package com.memoquest.service;

import com.memoquest.model.db.User;

/**
 * Created by franck on 30/10/2014.
 */
public class UserService {

    public User getUserActive() {

        /*
        aller rechercher le user actif
         */


        User user = new User();
        user.save();

        return user;
    }
}
