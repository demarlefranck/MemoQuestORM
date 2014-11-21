package com.memoquest.app.init;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.memoquest.app.R;
import com.memoquest.app.modal.ModalMessages;
import com.memoquest.model.db.User;
import com.memoquest.service.ConnexionService;
import com.memoquest.service.entity.UserService;

public class ConnectionActivity extends Activity  implements View.OnClickListener {

    private EditText loginText;
    private EditText passwordText;
    private TextView connexionText;
    private TextView signinText;
    private TextView passwordForbidText;
    private ConnexionService connexionService;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        connexionService = new ConnexionService();
        userService = new UserService();

        loginText = (EditText) this.findViewById(R.id.loginText);
        passwordText = (EditText) this.findViewById(R.id.passwordText);

        connexionText = (TextView) this.findViewById(R.id.connexionText);
        connexionText.setOnClickListener(this);

        signinText = (TextView) this.findViewById(R.id.signinText);
        signinText.setOnClickListener(this);

        passwordForbidText = (TextView) this.findViewById(R.id.passwordForbidText);
        passwordForbidText.setOnClickListener(this);
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.connexionText:


                User user = null;
                user = isAuthentifiate();


                if(user != null){

                    userService.edit(user, (long) -1);
                    userService.editUserToActive(user);

                    Intent intent = new Intent(ConnectionActivity.this, MenuActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.signinText:
                if (signinText != null){
                    signinText.setMovementMethod(LinkMovementMethod.getInstance());
                }
                break;
            case R.id.passwordForbidText:
                if (passwordForbidText != null){
                    passwordForbidText.setMovementMethod(LinkMovementMethod.getInstance());
                }
                break;
            default:
                ModalMessages.showWrongMessage(this, "Probleme Technique", this.getClass().getSimpleName() + "Methode: onClick():" + "Switch default.....");
                break;
        }
    }

    private User isAuthentifiate() {

        User user = null;
        String empty = "";
        String loginTextStr = String.valueOf(loginText.getText());
        String passwordTextStr = String.valueOf(passwordText.getText());

        if (loginTextStr.equals(empty)){

            ModalMessages.showWrongMessage(this, "erreur de saisie", "Veuillez saisir votre email");

        } else if (passwordTextStr.equals(empty)){

            ModalMessages.showWrongMessage(this, "erreur de saisie", "Veuillez saisir votre password");

        } else {

            user = connexionService.isAuthentifiate(loginTextStr, passwordTextStr);

            if (user == null) {
                ModalMessages.showWrongMessage(this, "erreur d'authentification", "L'authentification a échouée");
            }
        }
        return user;
    }
}