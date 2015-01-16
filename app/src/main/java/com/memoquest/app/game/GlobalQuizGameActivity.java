package com.memoquest.app.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.memoquest.app.R;
import com.memoquest.app.modal.ModalMessages;
import com.memoquest.model.GlobalQuiz;
import com.memoquest.model.db.Quiz;
import com.memoquest.model.db.QuizContent;
import com.memoquest.service.GlobalQuizService;
import com.memoquest.service.Utils.ObjetbunbleGetter;
import com.memoquest.service.entity.QuizService;

public class GlobalQuizGameActivity extends ActionBarActivity {


    private static final int REQUEST_SEARCH_CODE = 10;
    private static final int REQUEST_QUIZ_CONTENT_CODE = 11;
    private GlobalQuiz globalQuiz;
    private ObjetbunbleGetter objetbunbleGetter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        objetbunbleGetter = new ObjetbunbleGetter();
        globalQuiz = null;
        searchQuizId();

        setContentView(R.layout.activity_global_quiz_game);
    }

    private void searchQuizId() {
        Intent intent = new Intent(this, SearchForGameQuizActivity.class);
        intent.putExtra("filter", 0);
        startActivityForResult(intent,REQUEST_SEARCH_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SEARCH_CODE) {

            if(resultCode == RESULT_OK){
                long quizId = data.getLongExtra("quizId", -1);

                if(quizId != -1){
                    startGlobalQuizGame(quizId);
                }
                else{
                    Toast.makeText(getApplicationContext(),"probleme de recuperation de quiz id, onActivityResult() GlobalQuizGameActivity.class " , Toast.LENGTH_LONG).show();
                }
            }
        }
        else if (requestCode == REQUEST_QUIZ_CONTENT_CODE) {

            if(resultCode == RESULT_OK){
                int attemptsNumber = data.getIntExtra("attemptsNumber", -1);

                if(attemptsNumber != -1){
                     Toast.makeText(getApplicationContext(),"quizContent terminé avec nombre de tentative: " + attemptsNumber , Toast.LENGTH_LONG).show();
                   // startGlobalQuizGame(quizId);
                }
                else{
                    Toast.makeText(getApplicationContext(),"probleme de recuperation de attemptsNumber, onActivityResult() GlobalQuizGameActivity.class " , Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void startGlobalQuizGame(long quizId) {

        QuizService guizService = new QuizService();
        GlobalQuizService globalQuizService = new GlobalQuizService();

        Quiz quiz = guizService.findUniqueById(quizId);
        globalQuiz = globalQuizService.findGlobalQuiz(quiz);

        lunchAllQuizContentGame();

        Toast.makeText(getApplicationContext(),"reprendre ici, startGlobalQuizGame(),GlobalQuizGameActivity.class  quizId : " + quizId , Toast.LENGTH_LONG).show();
    }

    private void lunchAllQuizContentGame() {

        for(QuizContent quizContent : globalQuiz.getQuizContents()) {

            switch (quizContent.getQuestionType()) {
                case 0:
                    Intent intent0 = new Intent(this, Game0Activity.class);
                    intent0.putExtra("quizContentId", quizContent.getId());
                    startActivityForResult(intent0, REQUEST_QUIZ_CONTENT_CODE);


                    int quizFilter = objetbunbleGetter.getIntObjetbunbleValue(this, this.getIntent(), "filter");


                break;

                case 1:
                    Intent intent1 = new Intent(this, Game1Activity.class);
                    intent1.putExtra("quizContentId", quizContent.getId());
                    startActivity(intent1);
                break;

                default:
                    ModalMessages.showWrongMessage(this, "Probleme Technique", "getQuestionType switch default quizContent.getQuestionType():" + quizContent.getQuestionType());
                break;
            }
        }
    }

}
