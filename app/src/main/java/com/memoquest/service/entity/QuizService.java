package com.memoquest.service.entity;

import com.memoquest.dao.QuizDao;
import com.memoquest.model.db.Quiz;

import java.util.Date;
import java.util.List;

/**
 * Created by franck on 30/10/2014.
 */
public class QuizService {

    private QuizDao quizDao;

    public QuizService() {
        quizDao = new QuizDao();
    }

    public void edit(Quiz quiz, Long userId) {

        if (quiz.getCreateTime() == null) {
            quiz.setCreateTime(new Date());
        }
        if (quiz.getCreateUser() == null) {
            quiz.setCreateUser(userId);
        }

        quiz.setUpdateTime(new Date());
        quiz.setUpdateUser(userId);

        quiz.save();
    }

    public List<Quiz> findAll() {

        return quizDao.findAll();

    }

    public Quiz findUniqueById(long quizId) {
        return quizDao.findUniqueById(quizId);
    }


    public Quiz findUniqueByServeurId(Integer serverId) {
        return quizDao.findUniqueByServeurId(serverId);
    }
}
