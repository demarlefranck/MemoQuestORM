package com.memoquest.app.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.memoquest.app.R;
import com.memoquest.app.modal.ModalMessages;
import com.memoquest.model.GlobalQuiz;
import com.memoquest.model.db.Quiz;
import com.memoquest.model.db.QuizContent;
import com.memoquest.service.entity.QuizContentService;
import com.memoquest.service.entity.QuizService;

import java.util.List;

public class GlobalQuizActivity extends ActionBarActivity {

    private GlobalQuiz globalQuiz;
    private QuizService quizService;
    private QuizContentService quizContentService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_quiz);

        quizService = new QuizService();
        quizContentService = new QuizContentService();

        long quizId = getObjetbunbleValue();

        if(quizId != -1){
            globalQuiz = new GlobalQuiz();

            Quiz quiz = quizService.findUniqueById(quizId);

            globalQuiz.setQuiz(quiz);

            List<QuizContent> quizContents = quizContentService.findByQuiz(quiz.getServerId());

            globalQuiz.setQuizContents(quizContents);

            lunchAllQuizContentGame();

        }
        else{
            ModalMessages.showWrongMessage(this, "Probleme Technique", this.getClass().getSimpleName() + "onCreate(): " + "Probleme d'identification de ObjetbunbleValue  :  getObjetbunbleValue() = -1");
        }

    }

    private void lunchAllQuizContentGame() {

        for(QuizContent quizContent : globalQuiz.getQuizContents()){

            switch (quizContent.getQuestionType()){
                case 0:

                break;

                case 1:
                    Intent intent = new Intent(this, Game1Activity.class);
                    intent.putExtra("quizContentId", quizContent.getId());
                    startActivity(intent);
                break;
                default:
                    ModalMessages.showWrongMessage(this, "Probleme Technique", this.getClass().getSimpleName() + "getQuestionType switch default");
                break;
            }


        }

    }

    private long getObjetbunbleValue(){
        long objetbunbleValue = -1;
        String bundleKey = "quizId";

        Bundle objetbunble = this.getIntent().getExtras();

        if (objetbunble != null && objetbunble.containsKey(bundleKey)){

            objetbunbleValue = this.getIntent().getLongExtra(bundleKey, -1);

        } else{
            ModalMessages.showWrongMessage(this, "Probleme Technique", this.getClass().getSimpleName() + "onCreate(): " + "Probleme d'identification de ObjetbunbleValue");

        }
        return objetbunbleValue;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.global_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
