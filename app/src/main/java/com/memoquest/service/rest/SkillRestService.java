package com.memoquest.service.rest;

import com.memoquest.dao.rest.SkillRestDao;
import com.memoquest.model.db.Skill;

import java.util.List;

/**
 * Created by franck on 19/12/14.
 */
public class SkillRestService {

    private SkillRestDao skillRestDao;

    public SkillRestService(){
        skillRestDao = new SkillRestDao();
    }

    public List<Skill> getServerSkills(){
        return skillRestDao.getServerSkills();
    }
}
