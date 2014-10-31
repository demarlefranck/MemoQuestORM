package com.memoquest.model;

import com.memoquest.model.db.Quiz;
import com.memoquest.model.db.QuizContent;

import java.util.List;

/**
 * Created by franck on 30/10/2014.
 */
public class GlobalQuiz {

    private Quiz quiz;
    private List<QuizContent> quizContents;

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List<QuizContent> getQuizContents() {
        return quizContents;
    }

    public void setQuizContents(List<QuizContent> quizContents) {
        this.quizContents = quizContents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GlobalQuiz that = (GlobalQuiz) o;

        if (quiz != null ? !quiz.equals(that.quiz) : that.quiz != null) return false;
        if (quizContents != null ? !quizContents.equals(that.quizContents) : that.quizContents != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = quiz != null ? quiz.hashCode() : 0;
        result = 31 * result + (quizContents != null ? quizContents.hashCode() : 0);
        return result;
    }
}
