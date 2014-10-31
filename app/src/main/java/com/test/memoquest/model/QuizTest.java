package com.test.memoquest.model;

import com.memoquest.model.db.Quiz;

/**
 * Created by franck on 31/10/2014.
 */
public class QuizTest {

    public Quiz createOneQuiz(Integer number) {

        Quiz quiz = new Quiz();
        quiz.setName("name" + number);
        quiz.setServerId(number);
        quiz.setCreateUser(null);
        quiz.setCreateUser(null);
        quiz.setUpdateUser(null);
        quiz.setUpdateTime(null);

        return quiz;
    }
}