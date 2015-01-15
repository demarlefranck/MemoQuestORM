package com.memoquest.model.db;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by franck on 30/10/2014.
 */
@Table(name = "MQ_QUIZ")
public class Quiz extends Model implements Serializable{


    // `ROW_ID`, `NAME`,  `SKILL_ID`


    @Column(name = "ROW_ID")
    private Integer serverId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SKILL_ID", onDelete = Column.ForeignKeyAction.CASCADE)
    private Skill skill;

    @Column(name = "CREATED_BY")
    //  public int createUser;
    private Long createUser;

    @Column(name = "CREATED")
    private Date createTime;

    @Column(name = "UPDATED_BY")
    //   public int updateUser;
    private Long updateUser;

    @Column(name = "UPDATED")
    private Date updateTime;

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

/*
    public List<QuizContent> getQuizContents() {

        return getMany(QuizContent.class, "QUIZ_FK");
    }
*/

    @Override
    public String toString() {
        return "Quiz{" +
                "name='" + name + '\'' +
                //      ", skill=" + skill +
              //  ", quizContents=" + getQuizContents().toString() +
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

        Quiz quiz = (Quiz) o;

        if (createTime != null ? !createTime.equals(quiz.createTime) : quiz.createTime != null)
            return false;
        if (createUser != null ? !createUser.equals(quiz.createUser) : quiz.createUser != null)
            return false;
        if (name != null ? !name.equals(quiz.name) : quiz.name != null) return false;
        if (serverId != null ? !serverId.equals(quiz.serverId) : quiz.serverId != null)
            return false;
        if (skill != null ? !skill.equals(quiz.skill) : quiz.skill != null) return false;
        if (updateTime != null ? !updateTime.equals(quiz.updateTime) : quiz.updateTime != null)
            return false;
        if (updateUser != null ? !updateUser.equals(quiz.updateUser) : quiz.updateUser != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serverId != null ? serverId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (skill != null ? skill.hashCode() : 0);
        result = 31 * result + (createUser != null ? createUser.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateUser != null ? updateUser.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
