package com.memoquest.dao;

import com.activeandroid.query.Select;
import com.memoquest.model.db.Quiz;

import java.util.List;

/**
 * Created by franck on 15/11/14.
 */
public class QuizDao {

    public List<Quiz> getAll(){
        return new Select()
                .from(Quiz.class)
                .execute();
    }
}
