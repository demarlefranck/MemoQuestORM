package com.memoquest.app.game.addquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.memoquest.app.R;
import com.memoquest.app.modal.ModalMessages;
import com.memoquest.service.rest.QuizRestService;

public class AddQuizActivity extends ActionBarActivity {

    private QuizRestService quizRestService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz);
        quizRestService = new QuizRestService();
    }

    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.imageViewSearchByName:
                Intent intentGame = new Intent(AddQuizActivity.this, SearchForAddQuizActivity.class);
                intentGame.putExtra("filter", 0);
                startActivity(intentGame);
                break;

            case R.id.imageViewSearchBySkill:
                ModalMessages.showWrongMessage(this, "indisponibilité", "Fonctionnalité indisponible");
                break;

            default:
                ModalMessages.showWrongMessage(this, "indisponibilité", this.getClass().getSimpleName() + "onClick(): " + "Switch default.....");
                break;
        }
    }
}
