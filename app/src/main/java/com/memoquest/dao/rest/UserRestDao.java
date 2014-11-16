package com.memoquest.dao.rest;

import com.memoquest.model.db.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by franck on 16/11/14.
 */
public class UserRestDao {

    public User isAuthentifiateByServeur(String login, String password){


        //REMPLACER PAR SERVICE REST QUI RETOURNE LE USER


        User user = null;

        String passwordRef = toMD5("eip");

        password = toMD5(password);

        String login1 = "demarl_f";
        String login2 = "dupe_j";
        String login3 = "chave_k";
        String login4 = "fourni_c";
        String login5 = "grosje_s";
        String login6 = "devill_b";

        if(login.equals(login1) && password.equals(passwordRef)){

            user =  createUser(1, "demarle", "franck", "demarl_f@etna-alternance.net", login1, passwordRef, 0);

        } else if(login.equals(login2) && password.equals(passwordRef)){

            user =  createUser(2, "dupe", "jeremy", "dupe_j@etna-alternance.net", login2, passwordRef, 1);

        } else if(login.equals(login3) && password.equals(passwordRef)){

            user =  createUser(3, "chave", "kevin", "chave_k@etna-alternance.net", login3, passwordRef, 2);

        } else if(login.equals(login4) && password.equals(passwordRef)){

            user =  createUser(4, "fournier", "clement", "fourni_c@etna-alternance.net", login4, passwordRef, 3);

        } else if(login.equals(login5) && password.equals(passwordRef)){

            user =  createUser(5, "grosjean", "sebastien", "grosje_s@etna-alternance.net", login5, passwordRef, 4);

        } else if(login.equals(login6) && password.equals(passwordRef)){

            user = createUser(6, "devill_b", "bryan", "devill_b@etna-alternance.net", login6, passwordRef, 5);
        }
        return user;
    }

    /*
        A retirer apres avoir linker avec le service rest
     */
    private User createUser(Integer serverId, String lastName, String firstName, String email, String login, String password, Integer role){

        User user = new User();
        user.setServerId(serverId);
        user.setEmail(email);
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);

        return user;
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
