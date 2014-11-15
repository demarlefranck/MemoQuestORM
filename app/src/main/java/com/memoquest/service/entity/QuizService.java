package com.memoquest.service.entity;

import com.activeandroid.query.Select;
import com.memoquest.model.db.Quiz;

import java.util.Date;
import java.util.List;

/**
 * Created by franck on 30/10/2014.
 */
public class QuizService {

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

    public List<Quiz> getAll() {
        return new Select()
                .from(Quiz.class)
                .execute();
    }
}
