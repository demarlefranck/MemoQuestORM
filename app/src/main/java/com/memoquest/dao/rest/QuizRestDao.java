package com.memoquest.dao.rest;

import android.util.Log;

import com.memoquest.model.db.Quiz;
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
public class QuizRestDao {

    public List<Quiz> getQuizsBySkill(Skill skill) {

        List<Quiz> allQuizs = getServerQuizs();
        List<Quiz> quizs = new ArrayList<Quiz>();

        for (Quiz quiz : allQuizs) {
            if(quiz.getSkill().equals(quiz.getSkill())){
                quizs.add(quiz);
            }
        }

        return quizs;
    }

    public List<Quiz> getServerQuizs(){

        //remplacer cette recuperation par le service REST
        JsonContener jsonContener = new JsonContener();
        String quizsJsonString = jsonContener.getQuizJsonString();

        return jsonToQuizs(quizsJsonString);
    }

    private List<Quiz> jsonToQuizs(String quizsJsonString){

        List<Quiz> quizs = new ArrayList<Quiz>();
        JSONObject jsonObjects;

        try {

            MyDateUtils myDateUtils = new MyDateUtils();
            jsonObjects = new JSONObject(quizsJsonString);
            JSONArray jsonEntities = jsonObjects.getJSONArray("entities");

            Quiz quiz;
            for (int i = 0; i < jsonEntities.length(); i++) {

                JSONObject jsonObject = jsonEntities.getJSONObject(i);

                quiz = new Quiz();
                quiz.setServerId(parseInt(jsonObject.getString("ROW_ID")));
                quiz.setName(jsonObject.getString("NAME"));
                quiz.setCreateTime(myDateUtils.convertDateStringToDate(jsonObject.getString("CREATED")));
                quiz.setCreateUser((long) parseInt(jsonObject.getString("CREATED_BY")));
                quiz.setUpdateTime(myDateUtils.convertDateStringToDate(jsonObject.getString("UPDATED")));
                quiz.setUpdateUser((long) parseInt(jsonObject.getString("UPDATED_BY")));
                quizs.add(quiz);
            }

        } catch (JSONException e) {
            Log.d("*********************************", "Probleme lors de la conversion Json to listes: " + e.toString());
        } catch (ParseException e) {
            Log.d("*********************************", "Probleme lors de la conversion convertDateStringToDate : " + e.toString());
        }
        return quizs;
    }
}
