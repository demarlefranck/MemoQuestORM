package com.memoquest.dao;

import com.activeandroid.query.Select;
import com.memoquest.model.db.Quiz;

import java.util.List;

/**
 * Created by franck on 15/11/14.
 */
public class QuizDao {

    public List<Quiz> findAll(){
        return new Select()
                .from(Quiz.class)
                .execute();
    }

    public Quiz findUniqueById(Long quizId) {

        List<Quiz> quizs = new Select() .from(Quiz.class)
                                        .where("id = ?", quizId)
                                        .execute();

        if(quizs.size() > 1){
            throw new RuntimeException("Any quiz in findUniqueById()");
        }
        else if(quizs.size() == 1){
            return quizs.get(0);
        }
        return null;
    }

    public Quiz findUniqueByServeurId(Integer serverId) {

        List<Quiz> quizs = new Select() .from(Quiz.class)
                .where("ROW_ID = ?", serverId)
                .execute();

        if(quizs.size() > 1){
            throw new RuntimeException("Any quiz in findUniqueByServeurId()");
        }
        else if(quizs.size() == 1){
            return quizs.get(0);
        }
        return null;
    }
}
