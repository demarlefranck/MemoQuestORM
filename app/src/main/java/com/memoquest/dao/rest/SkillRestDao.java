package com.memoquest.dao.rest;

import android.util.Log;

import com.memoquest.model.db.Skill;
import com.memoquest.service.Utils.MyDateUtils;
import com.temp.json.JsonContener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Created by franck on 19/12/14.
 */
public class SkillRestDao {

    public List<Skill> getServerSkills(){

        //remplacer cette recuperation par le service REST
        JsonContener jsonContener = new JsonContener();
        String skillsJsonString = jsonContener.getSkillsJsonString();

        return jsonToSkills(skillsJsonString);
    }

    private List<Skill> jsonToSkills(String skillsJsonString){

        List<Skill> skills = new ArrayList<Skill>();
        JSONObject jsonObjects;

        try {

            MyDateUtils myDateUtils = new MyDateUtils();
            jsonObjects = new JSONObject(skillsJsonString);
            JSONArray jsonEntities = jsonObjects.getJSONArray("entities");

            Skill skill;
            for (int i = 0; i < jsonEntities.length(); i++) {

                JSONObject jsonObject = jsonEntities.getJSONObject(i);

                skill = new Skill();
                skill.setServerId(parseInt(jsonObject.getString("ROW_ID")));
                skill.setGrade(jsonObject.getString("GRADE"));
                skill.setSubject(jsonObject.getString("SUBJECT"));
                skill.setCategory(jsonObject.getString("CATEGORY"));
                skill.setSkill(jsonObject.getString("SKILL"));
                skill.setCreateTime(myDateUtils.convertDateStringToDate(jsonObject.getString("CREATED")));
                skill.setCreateUser((long) parseInt(jsonObject.getString("CREATED_BY")));
                skill.setUpdateTime(myDateUtils.convertDateStringToDate(jsonObject.getString("UPDATED")));
                skill.setUpdateUser((long) parseInt(jsonObject.getString("UPDATED_BY")));
                skills.add(skill);
            }

        } catch (JSONException e) {
            Log.d("*********************************", "Probleme lors de la conversion Json to listes: " + e.toString());
        } catch (ParseException e) {
            Log.d("*********************************", "Probleme lors de la conversion convertDateStringToDate : " + e.toString());
        }
        return skills;
    }
}
