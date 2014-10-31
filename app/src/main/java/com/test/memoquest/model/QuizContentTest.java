package com.test.memoquest.model;

import com.memoquest.model.db.Quiz;
import com.memoquest.model.db.QuizContent;

/**
 * Created by franck on 31/10/2014.
 */
public class QuizContentTest {

    public QuizContent createOneQuizContent(Integer number, Quiz quiz) {

        QuizContent quizContent = new QuizContent();
        quizContent.setQuiz(quiz);
        quizContent.setServerId(number);
        quizContent.setQuestionType(number);
        quizContent.setQuestion("question" + number);
        quizContent.setAnswerA("answerA" + number);
        quizContent.setAnswerB("answerB" + number);
        quizContent.setAnswerC("answerC" + number);
        quizContent.setAnswerD("answerD" + number);
        quizContent.setSolution("solution" + number);
        quizContent.setCreateUser(null);
        quizContent.setCreateUser(null);
        quizContent.setUpdateUser(null);
        quizContent.setUpdateTime(null);

        return quizContent;
    }
}
