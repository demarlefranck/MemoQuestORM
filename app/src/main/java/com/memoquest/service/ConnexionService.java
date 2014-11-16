package com.memoquest.service;

import android.content.Context;

import com.memoquest.dao.ConnexionDao;
import com.memoquest.model.db.User;
import com.memoquest.service.entity.UserService;

public class ConnexionService {

    private ConnexionDao connexionDao;
    private UserService userService;

    public ConnexionService(){
        connexionDao = new ConnexionDao();
        userService = new UserService();
    }

    public User isAuthentifiate(String login, String password) {
        return userService.isAuthentifiateByServeur(login, password);
    }

    public Boolean isConnected(Context context){
        return connexionDao.checkInternetConenction(context);
    }





}
