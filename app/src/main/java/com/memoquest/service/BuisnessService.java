package com.memoquest.service;

import com.activeandroid.ActiveAndroid;
import com.memoquest.model.GlobalQuiz;
import com.memoquest.model.db.Quiz;
import com.memoquest.model.db.QuizContent;
import com.memoquest.service.entity.QuizContentService;
import com.memoquest.service.entity.QuizService;
import com.memoquest.service.entity.UserService;

/**
 * Created by franck on 30/10/2014.
 */
public class BuisnessService {

    private UserService userService;
    private QuizContentService quizContentService;
    private QuizService quizService;

    public BuisnessService() {
        userService = new UserService();
        quizContentService = new QuizContentService();
        quizService = new QuizService();
    }

    public void editGlobalQuiz(GlobalQuiz globalQuiz) {

        ActiveAndroid.beginTransaction();

        try {
            quizService.edit(globalQuiz.getQuiz(), userService.getUserActive().getId());

            for (QuizContent quizContent : globalQuiz.getQuizContents()) {
                quizContentService.edit(quizContent, userService.getUserActive().getId());
            }

            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }

    public GlobalQuiz findGlobalQuiz(Quiz quiz) {

        GlobalQuiz globalQuiz = new GlobalQuiz();
        globalQuiz.setQuiz(quiz);
        globalQuiz.setQuizContents(quiz.getQuizContents());
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
