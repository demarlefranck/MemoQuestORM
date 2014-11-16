package com.test.memoquest.service;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.activeandroid.ActiveAndroid;
import com.memoquest.model.GlobalQuiz;
import com.memoquest.model.db.Quiz;
import com.memoquest.model.db.QuizContent;
import com.memoquest.service.GlobalQuizService;
import com.memoquest.service.entity.QuizContentService;
import com.memoquest.service.entity.QuizService;
import com.test.memoquest.model.QuizContentTest;
import com.test.memoquest.model.QuizTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by franck on 31/10/2014.
 */
public class BuisnessLayerTest extends AndroidTestCase {

    private GlobalQuizService buisnessLayer;

    private QuizService quizService;
    private QuizContentService quizContentService;
    private QuizServiceTest quizServiceTest;
    private QuizContentServiceTest quizContentServiceTest;
    private QuizContentTest quizContentTest;
    private QuizTest quizTest;

    public void setUp() {

        quizService = new QuizService();
        quizContentService = new QuizContentService();

        buisnessLayer = new GlobalQuizService();
        quizServiceTest = new QuizServiceTest();
        quizContentServiceTest = new QuizContentServiceTest();
        quizContentTest = new QuizContentTest();
        quizTest = new QuizTest();

        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        ActiveAndroid.initialize(context);

        quizContentServiceTest.testDeleteAll();
        quizServiceTest.testDeleteAll();
    }

    public void testEditOneGlobalQuizWithOneQuizContentOK() {

        /*
               check db is empty
         */
        assertEquals(0, quizService.getAll().size());
        assertEquals(0, quizContentService.getAll().size());

        Quiz quiz = quizTest.createOneQuiz(1);
        QuizContent quizContent1 = quizContentTest.createOneQuizContent(1, quiz);

        List<QuizContent> quizContents = new ArrayList<QuizContent>();
        quizContents.add(quizContent1);

        GlobalQuiz globalQuizExpected = new GlobalQuiz();
        globalQuizExpected.setQuiz(quiz);
        globalQuizExpected.setQuizContents(quizContents);

        buisnessLayer.editGlobalQuiz(globalQuizExpected);
        GlobalQuiz globalQuizResult = buisnessLayer.findGlobalQuiz(quiz);

        assertEquals(globalQuizResult, globalQuizExpected);
    }

    public void testEditOneGlobalQuizWithThreeQuizContentOK() {

        /*
               check db is empty
         */
        assertEquals(0, quizService.getAll().size());
        assertEquals(0, quizContentService.getAll().size());

        Quiz quiz = quizTest.createOneQuiz(1);

        QuizContent quizContent1 = quizContentTest.createOneQuizContent(1, quiz);
        QuizContent quizContent2 = quizContentTest.createOneQuizContent(2, quiz);
        QuizContent quizContent3 = quizContentTest.createOneQuizContent(3, quiz);

        List<QuizContent> quizContents = new ArrayList<QuizContent>();
        quizContents.add(quizContent1);
        quizContents.add(quizContent2);
        quizContents.add(quizContent3);

        GlobalQuiz globalQuizExpected = new GlobalQuiz();
        globalQuizExpected.setQuiz(quiz);
        globalQuizExpected.setQuizContents(quizContents);

        buisnessLayer.editGlobalQuiz(globalQuizExpected);

        GlobalQuiz globalQuizResult = buisnessLayer.findGlobalQuiz(quiz);
        assertEquals(globalQuizResult, globalQuizExpected);
    }

    public void testEditTreeGlobalQuizWithdifferentQuizContentOK() {

        /*
               check db is empty
         */
        assertEquals(0, quizService.getAll().size());
        assertEquals(0, quizContentService.getAll().size());

        Quiz quiz1 = quizTest.createOneQuiz(1);

        QuizContent quizContent1 = quizContentTest.createOneQuizContent(1, quiz1);
        List<QuizContent> quizContents1 = new ArrayList<QuizContent>();
        quizContents1.add(quizContent1);

        GlobalQuiz globalQuizExpected1 = new GlobalQuiz();
        globalQuizExpected1.setQuiz(quiz1);
        globalQuizExpected1.setQuizContents(quizContents1);

        buisnessLayer.editGlobalQuiz(globalQuizExpected1);

        GlobalQuiz globalQuizResult1 = buisnessLayer.findGlobalQuiz(quiz1);
        assertEquals(globalQuizResult1, globalQuizExpected1);
        assertEquals(globalQuizResult1.getQuizContents().size(), globalQuizExpected1.getQuizContents().size());

        assertEquals(1, quizService.getAll().size());
        assertEquals(1, quizContentService.getAll().size());

        Quiz quiz2 = quizTest.createOneQuiz(1);

        QuizContent quizContent2 = quizContentTest.createOneQuizContent(2, quiz2);
        QuizContent quizContent3 = quizContentTest.createOneQuizContent(3, quiz2);
        List<QuizContent> quizContents2 = new ArrayList<QuizContent>();
        quizContents2.add(quizContent2);
        quizContents2.add(quizContent3);

        GlobalQuiz globalQuizExpected2 = new GlobalQuiz();
        globalQuizExpected2.setQuiz(quiz2);
        globalQuizExpected2.setQuizContents(quizContents2);

        buisnessLayer.editGlobalQuiz(globalQuizExpected2);

        GlobalQuiz globalQuizResult2 = buisnessLayer.findGlobalQuiz(quiz2);
        assertEquals(globalQuizResult2, globalQuizExpected2);
        assertEquals(globalQuizResult2.getQuizContents().size(), globalQuizExpected2.getQuizContents().size());

        assertEquals(2, quizService.getAll().size());
        assertEquals(3, quizContentService.getAll().size());

        Quiz quiz3 = quizTest.createOneQuiz(3);

        QuizContent quizContent4 = quizContentTest.createOneQuizContent(4, quiz3);
        QuizContent quizContent5 = quizContentTest.createOneQuizContent(5, quiz3);
        QuizContent quizContent6 = quizContentTest.createOneQuizContent(6, quiz3);
        List<QuizContent> quizContents3 = new ArrayList<QuizContent>();
        quizContents3.add(quizContent4);
        quizContents3.add(quizContent5);
        quizContents3.add(quizContent6);

        GlobalQuiz globalQuizExpected3 = new GlobalQuiz();
        globalQuizExpected3.setQuiz(quiz3);
        globalQuizExpected3.setQuizContents(quizContents3);

        buisnessLayer.editGlobalQuiz(globalQuizExpected3);

        GlobalQuiz globalQuizResult3 = buisnessLayer.findGlobalQuiz(quiz3);
        assertEquals(globalQuizResult3, globalQuizExpected3);
        assertEquals(globalQuizResult3.getQuizContents().size(), globalQuizExpected3.getQuizContents().size());
        assertEquals(3, quizService.getAll().size());
        assertEquals(6, quizContentService.getAll().size());
    }

    public void testDeleteGlobalQuiz() {

        /*
               check db is empty
         */
        assertEquals(0, quizService.getAll().size());
        assertEquals(0, quizContentService.getAll().size());

        Quiz quiz = quizTest.createOneQuiz(1);

        QuizContent quizContent1 = quizContentTest.createOneQuizContent(1, quiz);
        QuizContent quizContent2 = quizContentTest.createOneQuizContent(2, quiz);
        QuizContent quizContent3 = quizContentTest.createOneQuizContent(3, quiz);

        List<QuizContent> quizContents = new ArrayList<QuizContent>();
        quizContents.add(quizContent1);
        quizContents.add(quizContent2);
        quizContents.add(quizContent3);

        GlobalQuiz globalQuizExpected = new GlobalQuiz();
        globalQuizExpected.setQuiz(quiz);
        globalQuizExpected.setQuizContents(quizContents);

        buisnessLayer.editGlobalQuiz(globalQuizExpected);
        assertEquals(1, quizService.getAll().size());
        assertEquals(3, quizContentService.getAll().size());

        GlobalQuiz globalQuizResult = buisnessLayer.findGlobalQuiz(quiz);
        assertEquals(globalQuizResult, globalQuizExpected);
        assertEquals(3, globalQuizResult.getQuizContents().size());

        buisnessLayer.deleteGlobalQuiz(globalQuizResult);

        assertEquals(0, quizService.getAll().size());
        assertEquals(0, quizContentService.getAll().size());
    }
}