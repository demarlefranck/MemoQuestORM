package com.memoquest.dao;

import com.activeandroid.query.Select;
import com.memoquest.model.db.User;

import java.util.List;

/**
 * Created by franck on 15/11/14.
 */
public class UserDao {

    public List<User> getAll(){
        return new Select().from(User.class)
                            // .orderBy("Name ASC")
                            .execute();
    }

    public User getUserActive() {

        List<User> users = new Select().from(User.class)
                                        .where("Active = ?", 1)
                                        .execute();

        if(users.size() > 1){
            throw new RuntimeException("Any User Active");
        }
        else if(users.size() == 1){
            return users.get(0);
        }
        return null;
    }
}
