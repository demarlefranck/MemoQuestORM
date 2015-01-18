package com.memoquest.service.entity;

import com.memoquest.dao.QuizContentDao;
import com.memoquest.model.db.QuizContent;

import java.util.Date;
import java.util.List;

/**
 * Created by franck on 30/10/2014.
 */
public class QuizContentService {

    private QuizContentDao quizContentDao;

    public QuizContentService() {
        quizContentDao = new QuizContentDao();
    }

    public void edit(QuizContent quizContent, Long userId) {

        if (quizContent.getCreateTime() == null) {
            quizContent.setCreateTime(new Date());
        }
        if (quizContent.getCreateUser() == null) {
            quizContent.setCreateUser(userId);
        }

        quizContent.setUpdateTime(new Date());
        quizContent.setUpdateUser(userId);

        quizContent.save();
    }

    public List<QuizContent> findAll() {
        return quizContentDao.findAll();
    }

    public List<QuizContent> findByQuiz(Integer quizServerId) {
        return quizContentDao.findByQuiz(quizServerId);

    }

    public QuizContent findUniqueById(long quizContentId) {
        return quizContentDao.findUniqueById(quizContentId);
    }
}
