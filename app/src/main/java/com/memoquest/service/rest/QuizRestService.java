package com.memoquest.service.rest;

import com.memoquest.dao.rest.get.RestGetAllQuizDao;
import com.memoquest.model.db.Quiz;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by franck on 19/12/14.
 */
public class QuizRestService {

    private RestGetAllQuizDao restGetAllQuizDao;

    public QuizRestService(){
        restGetAllQuizDao = new RestGetAllQuizDao();
    }

    public List<Quiz> getAllQuizsServer(){

        try {

            restGetAllQuizDao.execute();
            return restGetAllQuizDao.get();

        } catch (InterruptedException e) {
            throw new RuntimeException("Poblème de connexion au serveur");

        } catch (ExecutionException e) {
            throw new RuntimeException("Poblème de connexion au serveur");
        }
    }

   // public List<Quiz> getQuizsBySkill(Skill skill){
}
