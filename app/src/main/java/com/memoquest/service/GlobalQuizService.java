package com.memoquest.service;

import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.memoquest.model.GlobalQuiz;
import com.memoquest.model.db.Quiz;
import com.memoquest.model.db.QuizContent;
import com.memoquest.model.db.User;
import com.memoquest.service.entity.QuizContentService;
import com.memoquest.service.entity.QuizService;
import com.memoquest.service.entity.UserService;

import java.util.List;

/**
 * Created by franck on 30/10/2014.
 */
public class GlobalQuizService {

    private UserService userService;
    private QuizContentService quizContentService;
    private QuizService quizService;



    public GlobalQuizService() {
        userService = new UserService();
        quizContentService = new QuizContentService();
        quizService = new QuizService();
    }




    public void editGlobalQuiz(GlobalQuiz globalQuiz) {

        ActiveAndroid.beginTransaction();

        try {
            User user = userService.getUserActive();

            Log.d("Debug", "Probleme, ne retrouve pas de user actif    user = null    dans editGlobalQuiz()   ");

          //  quizService.edit(globalQuiz.getQuiz(), user.getId());
            quizService.edit(globalQuiz.getQuiz(), (long) 1);

            for (QuizContent quizContent : globalQuiz.getQuizContents()) {
               // quizContentService.edit(quizContent, user.getId());
                quizContentService.edit(quizContent, (long) 1);
            }

            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }

    public GlobalQuiz findGlobalQuiz(Quiz quiz) {

        GlobalQuiz globalQuiz = new GlobalQuiz();
        globalQuiz.setQuiz(quiz);

        List<QuizContent> quizContents = quizContentService.findByQuiz(quiz.getServerId());


        globalQuiz.setQuizContents(quizContents);
        return globalQuiz;
    }

    public GlobalQuiz deleteGlobalQuiz(GlobalQuiz globalQuiz) {

        ActiveAndroid.beginTransaction();

        try {

            for (QuizContent quizContent : globalQuiz.getQuizContents()) {
                quizContent.delete();
            }

            globalQuiz.getQuiz().delete();

            globalQuiz = null;

            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
        return globalQuiz;
    }
}
