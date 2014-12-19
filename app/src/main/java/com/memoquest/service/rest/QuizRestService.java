package com.memoquest.service.rest;

import com.memoquest.dao.rest.QuizRestDao;
import com.memoquest.model.db.Quiz;
import com.memoquest.model.db.Skill;

import java.util.List;

/**
 * Created by franck on 19/12/14.
 */
public class QuizRestService {

    private QuizRestDao quizRestDao;

    public QuizRestService(){
        quizRestDao = new QuizRestDao();
    }

    public List<Quiz> getQuizs(){
        return quizRestDao.getServerQuizs();
    }

    public List<Quiz> getQuizsBySkill(Skill skill){
        return quizRestDao.getQuizsBySkill(skill);
    }
}
