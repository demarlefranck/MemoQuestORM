package com.memoquest.model.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

/**
 * Created by franck on 30/10/2014.
 */
@Table(name = "MQ_QUIZ_CONTENT")
public class QuizContent extends Model {

    @Column(name = "ROW_ID")
    private Integer serverId;

    @Column(name = "QUESTION_TYPE")
    private Integer questionType;

    @Column(name = "QUESTION")
    private String question;

    @Column(name = "ANSWER_A")
    private String answerA;

    @Column(name = "ANSWER_B")
    private String answerB;

    @Column(name = "ANSWER_C")
    private String answerC;

    @Column(name = "ANSWER_D")
    private String answerD;

    @Column(name = "SOLUTION")
    private String solution;

    @Column(name = "QUIZ_SERVER_ID")
    private Integer quizServerId;

    @Column(name = "CREATED_BY")
    private Long createUser;

    @Column(name = "CREATED")
    private Date createTime;

    @Column(name = "UPDATED_BY")
    private Long updateUser;

    @Column(name = "UPDATED")
    private Date updateTime;

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Integer getQuizServerId() {
        return quizServerId;
    }

    public void setQuizServerId(Integer quizServerId) {
        this.quizServerId = quizServerId;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    @Override
    public String toString() {
        return "QuizContent{" +
                "serverId=" + serverId +
                ", questionType=" + questionType +
                ", question='" + question + '\'' +
                ", answerA='" + answerA + '\'' +
                ", answerB='" + answerB + '\'' +
                ", answerC='" + answerC + '\'' +
                ", answerD='" + answerD + '\'' +
                ", solution='" + solution + '\'' +
                ", quizServerId=" + quizServerId +
                ", createUser=" + createUser +
                ", createTime=" + createTime +
                ", updateUser=" + updateUser +
                ", updateTime=" + updateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        QuizContent that = (QuizContent) o;

        if (answerA != null ? !answerA.equals(that.answerA) : that.answerA != null) return false;
        if (answerB != null ? !answerB.equals(that.answerB) : that.answerB != null) return false;
        if (answerC != null ? !answerC.equals(that.answerC) : that.answerC != null) return false;
        if (answerD != null ? !answerD.equals(that.answerD) : that.answerD != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null)
            return false;
        if (createUser != null ? !createUser.equals(that.createUser) : that.createUser != null)
            return false;
        if (question != null ? !question.equals(that.question) : that.question != null)
            return false;
        if (questionType != null ? !questionType.equals(that.questionType) : that.questionType != null)
            return false;
        if (quizServerId != null ? !quizServerId.equals(that.quizServerId) : that.quizServerId != null)
            return false;
        if (serverId != null ? !serverId.equals(that.serverId) : that.serverId != null)
            return false;
        if (solution != null ? !solution.equals(that.solution) : that.solution != null)
            return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null)
            return false;
        if (updateUser != null ? !updateUser.equals(that.updateUser) : that.updateUser != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serverId != null ? serverId.hashCode() : 0;
        result = 31 * result + (questionType != null ? questionType.hashCode() : 0);
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (answerA != null ? answerA.hashCode() : 0);
        result = 31 * result + (answerB != null ? answerB.hashCode() : 0);
        result = 31 * result + (answerC != null ? answerC.hashCode() : 0);
        result = 31 * result + (answerD != null ? answerD.hashCode() : 0);
        result = 31 * result + (solution != null ? solution.hashCode() : 0);
        result = 31 * result + (quizServerId != null ? quizServerId.hashCode() : 0);
        result = 31 * result + (createUser != null ? createUser.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateUser != null ? updateUser.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
