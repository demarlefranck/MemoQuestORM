package com.memoquest.service.rest;

import com.memoquest.model.GlobalQuiz;
import com.memoquest.model.db.Quiz;

/**
 * Created by franck on 14/01/15.
 */
public class GlobalQuizRestService {

    private QuizContentRestService quizContentRestService;


    public GlobalQuizRestService() {
        quizContentRestService = new QuizContentRestService();
    }

    public GlobalQuiz getGlobalQuizRest(Quiz quiz) {
        GlobalQuiz globalQuiz = new  GlobalQuiz();
        globalQuiz.setQuiz(quiz);
        globalQuiz.setQuizContents(quizContentRestService.getQuizContentsByQuiz(quiz));
        return globalQuiz;
    }
}
