package com.memoquest.app.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.memoquest.app.R;
import com.memoquest.app.modal.ModalMessages;
import com.memoquest.model.GlobalQuiz;
import com.memoquest.model.db.Quiz;
import com.memoquest.model.db.QuizContent;
import com.memoquest.service.GlobalQuizService;
import com.memoquest.service.entity.QuizService;

import java.util.Date;

public class GlobalQuizGameActivity extends ActionBarActivity {

    private static final int REQUEST_SEARCH_CODE = 10;
    private static final int REQUEST_QUIZ_CONTENT_CODE = 11;
    private static final int REQUEST_GLOBAL_QUIZ_END_CODE = 11;
    private GlobalQuiz globalQuiz;
    private Date dateStart;
    private Date dateStop;
    private int errorNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        errorNumber = 0;
        globalQuiz = null;
        searchQuizId();
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
                int errorNumberTemp = data.getIntExtra("errorNumber", -1);

//MARCHE PAS LA REPONSE MET TROP DE TEMPS A REVENIR


            //    Toast.makeText(getApplicationContext(), "errorNumberTemp: " + errorNumberTemp, Toast.LENGTH_LONG).show();

                if(errorNumberTemp != -1){
                    errorNumber = errorNumber + errorNumberTemp;
                }
                else{
                    Toast.makeText(getApplicationContext(),"probleme de recuperation de errorNumber, onActivityResult() GlobalQuizGameActivity.class " , Toast.LENGTH_LONG).show();
                }
            }
        }
        /*
        else if (requestCode == REQUEST_GLOBAL_QUIZ_END_CODE) {

            if(resultCode == RESULT_OK){

              //  showResult();
            }
        }
        */
    }

    private void startGlobalQuizGame(long quizId) {

        QuizService guizService = new QuizService();
        GlobalQuizService globalQuizService = new GlobalQuizService();

        Quiz quiz = guizService.findUniqueById(quizId);
        globalQuiz = globalQuizService.findGlobalQuiz(quiz);

        lunchAllQuizContentGame();
     }

    private void lunchAllQuizContentGame() {

        dateStart = new Date();
        for(QuizContent quizContent : globalQuiz.getQuizContents()) {

            switch (quizContent.getQuestionType()) {
                case 0:
                    Intent intent0 = new Intent(this, Game0Activity.class);
                    intent0.putExtra("quizContentId", quizContent.getId());
                    startActivityForResult(intent0, REQUEST_QUIZ_CONTENT_CODE);
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
        dateStop = new Date();

/*
        Intent intent = new Intent(this, EndGlobalActivityActivity.class);
        startActivityForResult(intent,REQUEST_GLOBAL_QUIZ_END_CODE);
*/
        showResult();
    }

    private void showResult() {
        setContentView(R.layout.activity_global_quiz_game);

        TextView textViewErrorResult = (TextView) findViewById(R.id.textViewErrorResult);

        //   String textErrorResult = "vous avez fait " + errorNumber + " erreur" + addS(errorNumber);
        String textErrorResult = "";

        textViewErrorResult.setText(textErrorResult);

        TextView textViewTimeResult = (TextView) findViewById(R.id.textViewTimeResult);

        long diff = (dateStop.getTime() - dateStart.getTime()) ;

        int minutes = (int) (diff / 60);
        int secondes = (int) (diff % 60);

        String chrono = "chrono: ";
        if(minutes > 0){
            chrono += minutes + " minute" + addS(minutes) + " ";
        }
        if(secondes > 0){
            chrono += secondes +" seconde" + addS(secondes);
        }

        textViewTimeResult.setText(chrono);

    }

    private String addS(int nb){

        String result = null;

        if(nb > 1){
            result = "s";

        }else{
            result = "";
        }
        return result;
    }
}
