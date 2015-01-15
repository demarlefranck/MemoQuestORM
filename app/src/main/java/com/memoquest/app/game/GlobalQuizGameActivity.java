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
import com.memoquest.service.entity.QuizService;

public class GlobalQuizGameActivity extends ActionBarActivity {


    private static final int REQUEST_CODE = 10;
    private GlobalQuiz globalQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        globalQuiz = null;
        searchQuizId();

        setContentView(R.layout.activity_global_quiz_game);
    }

    private void searchQuizId() {
        Intent intent = new Intent(this, SearchForGameQuizActivity.class);
        intent.putExtra("filter", 0);
        startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10) {

            if(resultCode == RESULT_OK){
                long quizId = data.getLongExtra("quizId", -1);

                if(quizId != -1){
                   // Toast.makeText(getApplicationContext(),"quizId : " + quizId , Toast.LENGTH_LONG).show();
                    startGlobalQuizGame(quizId);
                }
                else{
                    Toast.makeText(getApplicationContext(),"probleme de recuperation de quiz id, onActivityResult() GlobalQuizGameActivity.class " , Toast.LENGTH_LONG).show();
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

                break;

                case 1:
                    Intent intent = new Intent(this, Game1Activity.class);
                    intent.putExtra("quizContentId", quizContent.getId());
                    startActivity(intent);
                break;

                default:
                    ModalMessages.showWrongMessage(this, "Probleme Technique", "getQuestionType switch default quizContent.getQuestionType():" + quizContent.getQuestionType());
                break;
            }
        }
    }

}
