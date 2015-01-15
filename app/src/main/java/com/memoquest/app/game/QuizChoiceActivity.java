package com.memoquest.app.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.memoquest.app.R;
import com.memoquest.app.game.addquiz.AddQuizActivity;
import com.memoquest.app.modal.ModalMessages;

public class QuizChoiceActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_choice);

    }

    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.imageViewQuizGo:
                Intent intentGame = new Intent(QuizChoiceActivity.this, GlobalQuizGameActivity.class);
                startActivity(intentGame);
                break;

            case R.id.imageViewAddQuiz:
                Intent intentSearchQuiz = new Intent(QuizChoiceActivity.this, AddQuizActivity.class);
                startActivity(intentSearchQuiz);
                break;


            default:
                ModalMessages.showWrongMessage(this, "indisponibilité", this.getClass().getSimpleName() + "onClick(): " + "Switch default.....");
                break;
        }
    }
}