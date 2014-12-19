package com.memoquest.dao.rest;

import android.util.Log;

import com.memoquest.model.db.Quiz;
import com.memoquest.model.db.QuizContent;
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
public class QuizContentRestDao {

    //bidouille pour remplacer le service rest avec param
    public List<QuizContent> getQuizContentsByQuiz(Quiz quiz){

        List<QuizContent> allQuizContents = getServerQuizContents();
        List<QuizContent> quizContents = new ArrayList<QuizContent>();

        for (QuizContent quizContent : allQuizContents) {
            if(quizContent.getQuizServerId().equals(quiz.getServerId())){
                quizContents.add(quizContent);
            }
        }

        return quizContents;
    }

    public List<QuizContent> getServerQuizContents(){

        //remplacer cette recuperation par le service REST
        JsonContener jsonContener = new JsonContener();
        String quizContentsJsonString = jsonContener.getQuizContentJsonString();

        return jsonToSkills(quizContentsJsonString);
    }

    private List<QuizContent> jsonToSkills(String quizContentsJsonString){

        List<QuizContent> quizContents = new ArrayList<QuizContent>();
        JSONObject jsonObjects;

        try {

            MyDateUtils myDateUtils = new MyDateUtils();
            jsonObjects = new JSONObject(quizContentsJsonString);
            JSONArray jsonEntities = jsonObjects.getJSONArray("entities");

            QuizContent quizContent;

            for (int i = 0; i < jsonEntities.length(); i++) {

                JSONObject jsonObject = jsonEntities.getJSONObject(i);

                quizContent = new QuizContent();
                quizContent.setServerId(parseInt(jsonObject.getString("ROW_ID")));
                quizContent.setQuestionType(parseInt(jsonObject.getString("QUESTION_TYPE")));
                quizContent.setQuestion(jsonObject.getString("QUESTION"));
                quizContent.setAnswerA(jsonObject.getString("ANSWER_A"));
                quizContent.setAnswerB(jsonObject.getString("ANSWER_B"));
                quizContent.setAnswerC(jsonObject.getString("ANSWER_C"));
                quizContent.setAnswerD(jsonObject.getString("ANSWER_D"));
                quizContent.setSolution(jsonObject.getString("SOLUTION"));
                quizContent.setQuizServerId(parseInt(jsonObject.getString("QUIZ_ID")));
                quizContent.setCreateTime(myDateUtils.convertDateStringToDate(jsonObject.getString("CREATED")));
                quizContent.setCreateUser((long) parseInt(jsonObject.getString("CREATED_BY")));
                quizContent.setUpdateTime(myDateUtils.convertDateStringToDate(jsonObject.getString("UPDATED")));
                quizContent.setUpdateUser((long) parseInt(jsonObject.getString("UPDATED_BY")));
                quizContents.add(quizContent);
            }

        } catch (JSONException e) {
            Log.d("*********************************", "Probleme lors de la conversion Json to listes: " + e.toString());
        } catch (ParseException e) {
            Log.d("*********************************", "Probleme lors de la conversion convertDateStringToDate : " + e.toString());
        }
        return quizContents;
    }
}
