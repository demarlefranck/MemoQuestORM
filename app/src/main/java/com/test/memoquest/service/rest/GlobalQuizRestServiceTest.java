package com.test.memoquest.service.rest;

import android.test.AndroidTestCase;

import com.memoquest.model.db.Quiz;
import com.memoquest.service.rest.GlobalQuizRestService;

/**
 * Created by franck on 13/01/15.
 */
public class GlobalQuizRestServiceTest extends AndroidTestCase {

    private GlobalQuizRestService globalQuizRestService;

    public void setUp() {
        globalQuizRestService = new GlobalQuizRestService();
    }

    public void testGetGlobalQuizRest() {

        Quiz quiz = new Quiz();
        quiz.setServerId(1);

        assertNotSame(0, globalQuizRestService.getGlobalQuizRest(quiz).getQuizContents().size());
    }
}
