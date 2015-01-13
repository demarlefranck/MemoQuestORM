package com.test.memoquest.service;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.activeandroid.ActiveAndroid;
import com.memoquest.model.db.User;
import com.memoquest.service.entity.UserService;

import java.util.List;

/**
 * Created by franck on 31/10/2014.
 */
public class UserServiceTest extends AndroidTestCase {

    private UserService userService;

    public void setUp() {
        userService = new UserService();

        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), "test_");
        ActiveAndroid.initialize(context);
    }

    public void testDeleteAll() {
        UserService userServiceTemp = new UserService();
        List<User> users = userServiceTemp.getAll();

        for (User user : users) {
            user.delete();
        }

        List<User> usersTempResult = userServiceTemp.getAll();
        assertEquals(0, usersTempResult.size());
    }

    public void testGetUserActiveOK() {

        /*
               check db is empty
         */
        testDeleteAll();
        assertEquals(0, userService.getAll().size());

        User user1 = new User();
        user1.setActive(1);
        userService.edit(user1, (long) -1);
        assertEquals(1, userService.getAll().size());


        User user2 = new User();
        user2.setActive(0);
        userService.edit(user2, (long) -1);
        assertEquals(2, userService.getAll().size());

        User userResult = null;
        userResult = userService.getUserActive();
        assertNotNull(userResult);

        testDeleteAll();
        assertEquals(0, userService.getAll().size());
    }


    public void testGetUserActiveKO() {
        /*
               check db is empty
         */
        testDeleteAll();
        assertEquals(0, userService.getAll().size());

        User user1 = new User();
        user1.setActive(1);
        userService.edit(user1, (long) -1);
        assertEquals(1, userService.getAll().size());


        User user2 = new User();
        user2.setActive(1);
        userService.edit(user2, (long) -1);
        assertEquals(2, userService.getAll().size());

        User userResult = null;
        Boolean trapRuntimeException = false;
        try {
            userResult = userService.getUserActive();
        }
        catch (RuntimeException e){
            assertEquals("Any User Active", e.getMessage());
            trapRuntimeException = true;
        }
        assertTrue(trapRuntimeException);
        assertNull(userResult);

        testDeleteAll();
        assertEquals(0, userService.getAll().size());
    }
}
