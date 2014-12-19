package com.memoquest.service.rest;

import com.memoquest.dao.rest.QuizContentRestDao;
import com.memoquest.model.db.Quiz;
import com.memoquest.model.db.QuizContent;

import java.util.List;

/**
 * Created by franck on 19/12/14.
 */
public class QuizContentRestService {

    private QuizContentRestDao quizContentRestDao;

    public QuizContentRestService(){
        quizContentRestDao = new QuizContentRestDao();
    }

    public List<QuizContent> getQuizContents(){
        return quizContentRestDao.getServerQuizContents();
    }

    public List<QuizContent> getQuizContentsByQuiz(Quiz quiz){
        return quizContentRestDao.getQuizContentsByQuiz(quiz);
    }
}
