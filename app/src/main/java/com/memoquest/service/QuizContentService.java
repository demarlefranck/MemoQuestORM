package com.memoquest.service;

import com.activeandroid.query.Select;
import com.memoquest.model.db.QuizContent;

import java.util.Date;
import java.util.List;

/**
 * Created by franck on 30/10/2014.
 */
public class QuizContentService {

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

    public List<QuizContent> getAll() {
        return new Select()
                .from(QuizContent.class)
                .execute();
    }
}
