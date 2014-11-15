package com.memoquest.service;

import android.content.Context;
import android.util.Log;

import com.memoquest.dao.ConnexionDao;
import com.memoquest.service.entity.UserService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ConnexionService {

    private ConnexionDao connexionDao;
    private UserService userService;
    private	static	String saltWord	= "PourSecuriserLeMotDePasse";

    public ConnexionService(){
        connexionDao = new ConnexionDao();
        userService = new UserService();
    }

    public Boolean isAuthentifiateByServeur(String loginTextStr, String passwordTextStr) {
/*
        Integer userId = connexionDao.isAuthentifiateByServeur(loginTextStr, passwordTextStr);

        if(userId != null){
            UserInternalBdd userInternalBdd = new UserInternalBdd();
            userInternalBdd.setId(userId);
            userInternalBdd.setEmail(loginTextStr);
            userInternalBdd.setPassword(toMD5(passwordTextStr));
            userInternalBdd.setActif(true);
            userInternalBdd.setCreateUser(userId);
            userInternalBdd.setCreateTime(MyDateUtils.getDateTime());
            userInternalBdd.setCreateUser(userId);
            userInternalBdd.setUpdateTime(MyDateUtils.getDateTime());
            userService.addUserInternalBddActif(userInternalBdd);

            return true;
        }
  */      return false;
    }

    public Boolean isConnected(Context context){
        return connexionDao.checkInternetConenction(context);
    }



    /*
        Permet de crypter le mot de passe en MD5

        voir pour SHA1
     */
    private String toMD5( String password ){

        MessageDigest messageDigest;

        StringBuilder 	stringBuilder 		= 	new StringBuilder();

        StringBuilder 	stringBuilderTemp 	= 	new StringBuilder();

        try {

            messageDigest = MessageDigest.getInstance( "MD5" );

            stringBuilderTemp.append(password);

            stringBuilderTemp.append(saltWord);

            messageDigest.update(stringBuilderTemp.toString().getBytes());

            byte[] 	byteData = messageDigest.digest();

            for (int i = 0; i < byteData.length; i++) {

                stringBuilder.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

            }
        } catch ( NoSuchAlgorithmException e ) {

          //  new TechnicalAppException("ConnexionService.class: toMD5(): Probleme lors de l'encryptage du mot de passe: " + e.toString());

            Log.e("ERROR", "ConnexionService.class: toMD5(): Probleme lors de l'encryptage du mot de passe: " + e.toString());

        }

        return stringBuilder.toString();
    }

}
