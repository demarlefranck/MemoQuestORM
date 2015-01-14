package com.memoquest.service.rest;

import com.memoquest.dao.rest.get.RestGetAllQuizContentDao;
import com.memoquest.model.db.Quiz;
import com.memoquest.model.db.QuizContent;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by franck on 19/12/14.
 */
public class QuizContentRestService {

    private RestGetAllQuizContentDao restGetAllQuizContentDao;

    public QuizContentRestService(){
        restGetAllQuizContentDao = new RestGetAllQuizContentDao();
    }

    public List<QuizContent> getQuizContentsByQuiz(Quiz quiz){


        try {

            restGetAllQuizContentDao.setQuizId(quiz.getServerId());
            restGetAllQuizContentDao.execute();
            return restGetAllQuizContentDao.get();

        } catch (InterruptedException e) {
            throw new RuntimeException("Poblème de connexion au serveur");

        } catch (ExecutionException e) {
            throw new RuntimeException("Poblème de connexion au serveur");
        }
    }
}
