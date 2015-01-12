package com.memoquest.service.rest;

import com.memoquest.dao.rest.QuizRestDaoOLD;
import com.memoquest.model.db.Quiz;
import com.memoquest.model.db.Skill;

import java.util.List;

/**
 * Created by franck on 19/12/14.
 */
public class QuizRestService {

    private QuizRestDaoOLD quizRestDao;

    public QuizRestService(){
        quizRestDao = new QuizRestDaoOLD();
    }

    public List<Quiz> getQuizs(){
        return quizRestDao.getServerQuizs();
    }

    public List<Quiz> getQuizsBySkill(Skill skill){
        return quizRestDao.getQuizsBySkill(skill);
    }
}
