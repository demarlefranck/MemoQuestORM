package com.test.memoquest.service;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.activeandroid.ActiveAndroid;
import com.memoquest.model.db.Quiz;
import com.memoquest.service.entity.QuizService;
import com.test.memoquest.model.QuizTest;

import java.util.List;

/**
 * Created by franck on 31/10/2014.
 */
public class QuizServiceTest extends AndroidTestCase {

    private QuizService quizService;
    private QuizTest quizTest;

    public void setUp() {
        quizService = new QuizService();
        quizTest = new QuizTest();

        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        ActiveAndroid.initialize(context);
    }

    public void testEditDeleteGetAllQuizContentOK() {

        /*
               check db is empty
         */
        assertEquals(0, quizService.getAll().size());

        Quiz quiz1 = quizTest.createOneQuiz(1);
        quizService.edit(quiz1, (long) 1);

        assertEquals(1, quizService.getAll().size());

        Quiz quiz2 = quizTest.createOneQuiz(2);
        quizService.edit(quiz2, (long) 2);

        assertEquals(2, quizService.getAll().size());

        quiz2.delete();

        assertEquals(1, quizService.getAll().size());

        Quiz quiz3 = quizTest.createOneQuiz(3);
        quizService.edit(quiz3, (long) 3);

        testDeleteAll();

        assertEquals(0, quizService.getAll().size());
    }

    /*
        Methode utilisee dans BuisnessLayerTest
     */
    public void testDeleteAll() {
        QuizService quizServiceTemp = new QuizService();
        List<Quiz> quizs = quizServiceTemp.getAll();

        for (Quiz quiz : quizs) {
            quiz.delete();
        }

        List<Quiz> quizContentsResult = quizServiceTemp.getAll();
        assertEquals(0, quizContentsResult.size());
    }
}
