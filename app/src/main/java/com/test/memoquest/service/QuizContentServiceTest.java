package com.test.memoquest.service;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.activeandroid.ActiveAndroid;
import com.memoquest.model.db.Quiz;
import com.memoquest.model.db.QuizContent;
import com.memoquest.service.QuizContentService;
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
        assertEquals(0, quizContentService.getAll().size());

        Quiz quiz = quizTest.createOneQuiz(1);

        QuizContent quizContent1 = quizContentTest.createOneQuizContent(1, quiz);
        quizContentService.edit(quizContent1, (long) 1);

        assertEquals(1, quizContentService.getAll().size());

        QuizContent quizContent2 = quizContentTest.createOneQuizContent(2, quiz);
        quizContentService.edit(quizContent2, (long) 2);

        assertEquals(2, quizContentService.getAll().size());

        quizContent2.delete();

        assertEquals(1, quizContentService.getAll().size());

        QuizContent quizContent3 = quizContentTest.createOneQuizContent(2, quiz);
        quizContentService.edit(quizContent3, (long) 2);

        testDeleteAll();

        assertEquals(0, quizContentService.getAll().size());
    }

    /*
        Methode utilisee dans BuisnessLayerTest
     */
    public void testDeleteAll() {
        QuizContentService quizContentServiceTemp = new QuizContentService();

        List<QuizContent> quizContents = quizContentServiceTemp.getAll();

        for (QuizContent quizContent : quizContents) {
            quizContent.delete();
        }

        List<QuizContent> quizContentsResult = quizContentServiceTemp.getAll();
        assertEquals(0, quizContentsResult.size());
    }
}
