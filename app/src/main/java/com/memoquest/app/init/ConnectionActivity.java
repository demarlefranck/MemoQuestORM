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
import com.memoquest.service.ConnexionService;

public class ConnectionActivity extends Activity  implements View.OnClickListener {

    private ModalMessages modalMessages;
    private EditText loginText;
    private EditText passwordText;
    private TextView connexionText;
    private TextView signinText;
    private TextView passwordForbidText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        modalMessages = new ModalMessages();

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
                if(isAuthentifiate()){
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
                modalMessages.showWrongMessage(this, "Probleme Technique", this.getClass().getSimpleName() + "Methode: onClick():" + "Switch default.....");
                break;
        }
    }

    private Boolean isAuthentifiate() {

        ConnexionService connexionService = new ConnexionService();

        String empty = "";
        String loginTextStr = String.valueOf(loginText.getText());
        String passwordTextStr = String.valueOf(passwordText.getText());

        if (loginTextStr.equals(empty)){
            modalMessages.showWrongMessage(this, "erreur de saisie", "Veuillez saisir votre email");
            return false;

        } else if (passwordTextStr.equals(empty)){
            modalMessages.showWrongMessage(this, "erreur de saisie", "Veuillez saisir votre password");
            return false;

        } else {
          //  try {
                if (connexionService.isAuthentifiateByServeur(loginTextStr, passwordTextStr) == false) {
                    modalMessages.showWrongMessage(this, "erreur d'authentification", "L'authentification a échouée");
                    return false;
                }

        /*
        } catch (TechnicalAppException e) {
                Log.e("ERROR", e.toString());
                Alerte.showAlertDialog("Probleme Systeme", this.getClass().getSimpleName() + "isAuthentifiate(): " + e.toString(), this);
            } catch (FonctionalAppException e) {
                Log.e("ERROR", e.toString());
                Alerte.showAlertDialog("Probleme Systeme", this.getClass().getSimpleName() + "isAuthentifiate(): " + e.toString(), this);
            }
        */
        }
        return true;
    }
}