package com.memoquest.dao.rest;

import com.memoquest.model.db.User;

/**
 * Created by franck on 16/11/14.
 */
public class UserRestDao {

    public User isAuthentifiateByServeur(String loginTextStr, String passwordTextStr){


        //REMPLACER PAR SERVICE REST QUI RETOURNE LE USER

        String password = "eip";

        String login1 = "demarl_f";
        String login2 = "dupe_j";
        String login3 = "chave_k";
        String login4 = "fourni_c";
        String login5 = "grosje_s";
        String login6 = "devill_b";

        if(loginTextStr.equals(login1) && passwordTextStr.equals(password)){

            return createUser(1, "demarle", "franck", "demarl_f@etna-alternance.net", login1, password, 0);

        } else if(loginTextStr.equals(login2) && passwordTextStr.equals(password)){

            return createUser(2, "dupe", "jeremy", "dupe_j@etna-alternance.net", login2, password, 1);

        } else if(loginTextStr.equals(login3) && passwordTextStr.equals(password)){

            return createUser(3, "chave", "kevin", "chave_k@etna-alternance.net", login3, password, 2);

        } else if(loginTextStr.equals(login4) && passwordTextStr.equals(password)){

            return createUser(4, "fournier", "clement", "fourni_c@etna-alternance.net", login4, password, 3);

        } else if(loginTextStr.equals(login5) && passwordTextStr.equals(password)){

            return createUser(5, "grosjean", "sebastien", "grosje_s@etna-alternance.net", login5, password, 4);

        } else if(loginTextStr.equals(login6) && passwordTextStr.equals(password)){

            return createUser(6, "devill_b", "bryan", "devill_b@etna-alternance.net", login6, password, 5);
        }
        return null;
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
}
