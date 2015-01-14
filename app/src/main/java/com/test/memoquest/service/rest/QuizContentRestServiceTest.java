package com.test.memoquest.service.rest;

import android.test.AndroidTestCase;

import com.memoquest.model.db.Quiz;
import com.memoquest.service.rest.QuizContentRestService;

/**
 * Created by franck on 13/01/15.
 */
public class QuizContentRestServiceTest extends AndroidTestCase {

    private QuizContentRestService quizContentRestService;

    public void setUp() {
        quizContentRestService = new QuizContentRestService();
    }

    public void testGetAllQuizsServer() {

        Quiz quiz = new Quiz();
        quiz.setServerId(1);

        assertNotSame(0, quizContentRestService.getQuizContentsByQuiz(quiz).size());
    }

    public void testGetAllQuizsServerFake() {
        assertTrue("pensez a voir avec bry pour le format date de created et updated", false);
    }
}
