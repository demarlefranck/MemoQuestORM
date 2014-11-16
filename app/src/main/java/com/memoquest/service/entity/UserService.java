package com.memoquest.service.entity;

import com.memoquest.dao.UserDao;
import com.memoquest.dao.rest.UserRestDao;
import com.memoquest.model.db.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        return userRestDao.isAuthentifiateByServeur(login, toMD5(password));
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


    /*
    Permet de crypter le mot de passe en MD5

    voir pour SHA1
 */
    private String toMD5( String password ){

        String saltWord	= "PourSecuriserLeMotDePasse";
        MessageDigest messageDigest;
        StringBuilder 	stringBuilder 		= 	new StringBuilder();
        StringBuilder 	stringBuilderTemp 	= 	new StringBuilder();

        try {

            //messageDigest = MessageDigest.getInstance( "MD5" );


            //testSHA1
            messageDigest = MessageDigest.getInstance( "SHA1" );
            stringBuilderTemp.append(password);
            stringBuilderTemp.append(saltWord);
            messageDigest.update(stringBuilderTemp.toString().getBytes());

            byte[] 	byteData = messageDigest.digest();

            for (int i = 0; i < byteData.length; i++) {

                stringBuilder.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

            }
        } catch ( NoSuchAlgorithmException e ) {

            throw new RuntimeException(this.getClass().getSimpleName() + "Methode: toMD5(): Probleme lors de l'encryptage du mot de passe: " + e.toString());
        }

        return stringBuilder.toString();
    }
}