package com.test.memoquest.service;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.activeandroid.ActiveAndroid;
import com.memoquest.model.db.Quiz;
import com.memoquest.model.db.QuizContent;
import com.memoquest.service.entity.QuizContentService;
import com.test.memoquest.model.QuizContentTest;
import com.test.memoquest.model.QuizTest;

import java.util.List;

/**
 * Created by franck on 31/10/2014.
 */
public class QuizContentServiceTest extends AndroidTestCase {

    private QuizContentService quizContentService;
    private QuizContentTest quizContentTest;
    ;
    private QuizTest quizTest;

    public void setUp() {
        quizContentService = new QuizContentService();
        quizContentTest = new QuizContentTest();
        quizTest = new QuizTest();

        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        ActiveAndroid.initialize(context);

        testDeleteAll();
    }

    public void testEditDeleteGetAllQuizContentOK() {

        /*
               check db is empty
         */
        assertEquals(0, quizContentService.findAll().size());

        Quiz quiz = quizTest.createOneQuiz(1);

        QuizContent quizContent1 = quizContentTest.createOneQuizContent(1, quiz);

        quizContentService.edit(quizContent1, (long) 1);

        assertEquals(1, quizContentService.findAll().size());

        QuizContent quizContent2 = quizContentTest.createOneQuizContent(2, quiz);
        quizContentService.edit(quizContent2, (long) 2);

        assertEquals(2, quizContentService.findAll().size());

        quizContent2.delete();

        assertEquals(1, quizContentService.findAll().size());

        QuizContent quizContent3 = quizContentTest.createOneQuizContent(2, quiz);
        quizContentService.edit(quizContent3, (long) 2);

        testDeleteAll();

        assertEquals(0, quizContentService.findAll().size());
    }

    /*
        Methode utilisee dans GlobalQuizServiceTest
     */
    public void testDeleteAll() {
        QuizContentService quizContentServiceTemp = new QuizContentService();

        List<QuizContent> quizContents = quizContentServiceTemp.findAll();

        for (QuizContent quizContent : quizContents) {
            quizContent.delete();
        }

        List<QuizContent> quizContentsResult = quizContentServiceTemp.findAll();
        assertEquals(0, quizContentsResult.size());
    }
}
