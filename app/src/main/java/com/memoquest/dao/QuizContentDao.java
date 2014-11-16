package com.memoquest.dao;

import com.activeandroid.query.Select;
import com.memoquest.model.db.QuizContent;

import java.util.List;

/**
 * Created by franck on 15/11/14.
 */
public class QuizContentDao {

    public List<QuizContent> getAll(){
        return new Select()
                .from(QuizContent.class)
                .execute();
    }
}
