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

    public List<QuizContent> findByQuiz(Integer quizServerId) {
        return new Select().from(QuizContent.class)
                           .where("QUIZ_SERVER_ID = ?", quizServerId)
                           .execute();
    }

    public QuizContent findUniqueById(long quizContentId) {

        List<QuizContent> quizContents = new Select().from(QuizContent.class)
                                                    .where("id = ?", quizContentId)
                                                    .execute();

        if(quizContents.size() > 1){
            throw new RuntimeException("Any quiz in findUniqueById()");
        }
        else if(quizContents.size() == 1){
            return quizContents.get(0);
        }
        return null;
    }
}
