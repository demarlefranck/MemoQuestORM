package com.memoquest.app.init;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.memoquest.app.R;
import com.memoquest.model.GlobalQuiz;
import com.memoquest.model.db.Quiz;
import com.memoquest.model.db.QuizContent;
import com.memoquest.service.BuisnessLayer;
import com.memoquest.service.QuizService;
import com.test.memoquest.model.QuizContentTest;
import com.test.memoquest.model.QuizTest;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BuisnessLayer buisnessLayer = new BuisnessLayer();

        QuizService quizService = new QuizService();

        QuizContentTest quizContentTest = new QuizContentTest();
        QuizTest quizTest = new QuizTest();

        Quiz quiz = quizTest.createOneQuiz(1);

        QuizContent quizContent1 = quizContentTest.createOneQuizContent(1, quiz);
        QuizContent quizContent2 = quizContentTest.createOneQuizContent(2, quiz);
        QuizContent quizContent3 = quizContentTest.createOneQuizContent(3, quiz);

        List<QuizContent> quizContents = new ArrayList<QuizContent>();
        quizContents.add(quizContent1);
        quizContents.add(quizContent2);
        quizContents.add(quizContent3);

        GlobalQuiz globalQuizExpected = new GlobalQuiz();
        globalQuizExpected.setQuiz(quiz);
        globalQuizExpected.setQuizContents(quizContents);


        Log.d("DEBUBQuizGetAll", quizService.getAll().toString());

        buisnessLayer.editGlobalQuiz(globalQuizExpected);
        GlobalQuiz globalQuizResult = buisnessLayer.findGlobalQuiz(quiz);


        Log.d("DEBUBQuizGetAll", quizService.getAll().toString());

        for (QuizContent quizContentItem : globalQuizResult.getQuizContents()) {

            Log.d("DEBUB", quizContentItem.toString());
        }

        buisnessLayer.deleteGlobalQuiz(globalQuizExpected);
        GlobalQuiz globalQuizResult2 = buisnessLayer.findGlobalQuiz(quiz);

        for (QuizContent quizContentItem : globalQuizResult2.getQuizContents()) {

            Log.d("DEBUB", quizContentItem.toString());
        }

        quiz.delete();

        Log.d("DEBUBQuiz", quiz.toString());


        Log.d("DEBUBQuizGetAll", quizService.getAll().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
