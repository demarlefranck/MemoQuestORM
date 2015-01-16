package com.memoquest.app.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.memoquest.app.R;
import com.memoquest.app.modal.ModalMessages;
import com.memoquest.model.db.QuizContent;
import com.memoquest.service.entity.QuizContentService;

public class Game0Activity extends ActionBarActivity implements View.OnClickListener {

    private QuizContent quizContent;
    private int attemptsNumber;
    private QuizContentService quizContentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game0);

        quizContentService = new QuizContentService();
        attemptsNumber = 0;

        long quizContentId = getObjetbunbleValue();

        if (quizContentId != -1) {

            quizContent = quizContentService.findUniqueById(quizContentId);
        } else {
            ModalMessages.showWrongMessage(this, "Probleme Technique", this.getClass().getSimpleName() + "onCreate(): " + "Probleme d'identification de ObjetbunbleValue  :  getObjetbunbleValue() = -1");
        }
    }

    private long getObjetbunbleValue() {

        long objetbunbleValue = -1;
        String bundleKey = "quizContentId";
        Bundle objetbunble = this.getIntent().getExtras();

        if (objetbunble != null && objetbunble.containsKey(bundleKey)) {

            objetbunbleValue = this.getIntent().getLongExtra(bundleKey, -1);

        } else {
            ModalMessages.showWrongMessage(this, "Probleme Technique", this.getClass().getSimpleName() + "onCreate(): " + "Probleme d'identification de ObjetbunbleValue");
        }
        return objetbunbleValue;
    }


    @Override
    protected void onStart() {
        super.onStart();

        TextView textViewQuestion = (TextView) findViewById(R.id.textViewQuestion);
        if(quizContent.getQuestion() != null){
            textViewQuestion.setText(quizContent.getQuestion());
        }
        if(quizContent.getAnswerA() != null){
            TextView textViewAnswerA = (TextView) findViewById(R.id.textViewAnswerA);
            textViewAnswerA.setText(quizContent.getAnswerA());
        }
        if(quizContent.getAnswerB() != null){
            TextView textViewAnswerB = (TextView) findViewById(R.id.textViewAnswerB);
            textViewAnswerB.setText(quizContent.getAnswerB());
        }
        if(quizContent.getAnswerC() != null){
            TextView textViewAnswerC = (TextView) findViewById(R.id.textViewAnswerC);
            textViewAnswerC.setText(quizContent.getAnswerC());
        }
        if(quizContent.getAnswerD() != null){
            TextView textViewAnswerD = (TextView) findViewById(R.id.textViewAnswerD);
            textViewAnswerD.setText(quizContent.getAnswerD());
        }
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.textViewAnswerA) {
            checkAnswer(quizContent.getAnswerA());
        }
        else if(view.getId() == R.id.textViewAnswerB) {
            checkAnswer(quizContent.getAnswerB());
        }
        else if(view.getId() == R.id.textViewAnswerC) {
            checkAnswer(quizContent.getAnswerC());
        }
        else if(view.getId() == R.id.textViewAnswerD) {
            checkAnswer(quizContent.getAnswerD());
        }
    }

    public void checkAnswer(String answerGamer){

        ModalMessages modalMessages = new ModalMessages();

        if(answerGamer.equals(quizContent.getSolution())){
            modalMessages.showGoodMessage(this, "Bien jouer", "Bonne réponse");

            Intent returnIntent = new Intent();
            returnIntent.putExtra("attemptsNumber", attemptsNumber);
            setResult(RESULT_OK, returnIntent);
            finish();

        }
        else{
            modalMessages.showWrongMessage(this, "Désolé", "Mauvaise réponse");
            attemptsNumber++;
        }
    }
}