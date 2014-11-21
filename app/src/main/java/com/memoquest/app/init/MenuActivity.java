package com.memoquest.app.init;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.memoquest.app.R;
import com.memoquest.app.game.QuizChoiceActivity;
import com.memoquest.app.modal.ModalMessages;
import com.memoquest.service.entity.UserService;

public class MenuActivity extends Activity implements View.OnClickListener {

    private TextView manageListText;
    private TextView playText;
    private TextView changeUserText;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        userService = new UserService();
/*
        manageListText = (TextView) this.findViewById(R.id.manageListText);
        manageListText.setOnClickListener(this);

        changeUserText = (TextView) this.findViewById(R.id.changeUserText);
        changeUserText.setOnClickListener(this);

        playText = (TextView) this.findViewById(R.id.playTextView);
        playText.setOnClickListener(this);

  */

    }


    public void onClick(View v) {


        switch (v.getId()) {


            case R.id.imageViewQuiz:
                userService.editAllUserToNoActif();
                Intent intentGameChoice = new Intent(MenuActivity.this, QuizChoiceActivity.class);
                startActivity(intentGameChoice);
            break;

            case R.id.imageViewScores:
                ModalMessages.showWrongMessage(this, "indisponibilité", "Fonctionnalité indisponible");
            break;

            case R.id.imageViewParameters:
                ModalMessages.showWrongMessage(this, "indisponibilité", "Fonctionnalité indisponible");
            break;

            case R.id.imageViewLogout:
                userService.editAllUserToNoActif();
                Intent intentSwitchUser = new Intent(MenuActivity.this, SwitchUserActivity.class);
                startActivity(intentSwitchUser);
            break;

            default:
                ModalMessages.showWrongMessage(this, "indisponibilité", this.getClass().getSimpleName() + "onClick(): " + "Switch default.....");
            break;


        }

    }

}
