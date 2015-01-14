package com.memoquest.dao.rest.get;

import android.os.AsyncTask;
import android.util.Log;

import com.memoquest.model.db.QuizContent;
import com.memoquest.service.Utils.MyDateUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class RestGetAllQuizContentDao extends AsyncTask<Void, Void, List<QuizContent> > {

    private int userId;
    private int quizId;
    public void setUserId(int id) {
        this.userId = id;
    }
    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    protected List<QuizContent> doInBackground(Void... params) {

        List<QuizContent> quizContents = null;

        final String url = "http://memoquest.fr/MemoQuest/app_dev.php/api/quizzes/" + quizId + "/quiz/contents";

        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);

        try {
            HttpResponse httpResponse = httpclient.execute(httpGet);
            int statusCode = httpResponse.getStatusLine().getStatusCode();

            if (statusCode == 200) {

                quizContents = httpEntityToObject(httpResponse.getEntity());

            } else {
               // new TechnicalAppException("RestGetListesDaoTest.class: Probleme lors de la recuperation des listes");
            }
        } catch (Exception e) {

            e.toString();
        }


        Log.d("debug         quizs.toString:        ", quizContents.toString());

        return quizContents;
    }

    public List<QuizContent> httpEntityToObject(HttpEntity httpEntity){

        StringBuilder builder = new StringBuilder();

        try {
            InputStream inputStream = httpEntity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.toString();
        }
       return stringToQuizContents(builder.toString());
    }

    public List<QuizContent> stringToQuizContents(String string){

        MyDateUtils myDateUtils = new MyDateUtils();
        List<QuizContent> quizContents = new ArrayList<QuizContent>();

        try {

            JSONObject jsonObjects = new JSONObject(string);
            JSONArray jsonEntities = jsonObjects.getJSONArray("entities");
            QuizContent quizContent;

            for (int i = 0; i < jsonEntities.length(); i++) {

                JSONObject jsonObject = jsonEntities.getJSONObject(i);


                /*
                    pb avec le format de bry 2014-10-28T10:25:00+0100
                 */
               quizContent = new QuizContent();

                quizContent.setServerId(parseInt(jsonObject.getString("id")));
                quizContent.setQuestionType(parseInt(jsonObject.getString("question_type")));
                quizContent.setQuestion(jsonObject.getString("question"));
                quizContent.setAnswerA(jsonObject.getString("answer_a"));
                quizContent.setAnswerB(jsonObject.getString("answer_b"));
                quizContent.setAnswerC(jsonObject.getString("answer_c"));
                quizContent.setAnswerD(jsonObject.getString("answer_d"));
                quizContent.setSolution(jsonObject.getString("solution"));
                quizContent.setQuizServerId(quizId);
               // quizContent.setCreateTime(myDateUtils.convertDateStringToDate(jsonObject.getString("created")));
                quizContent.setCreateUser((long) parseInt(jsonObject.getString("created_by")));
               // quizContent.setUpdateTime(myDateUtils.convertDateStringToDate(jsonObject.getString("updated")));
                quizContent.setUpdateUser((long) parseInt(jsonObject.getString("updated_by")));

                quizContents.add(quizContent);
            }
        }
        catch (JSONException e) {
            Log.d("*********************************", "Probleme lors de la conversion Json to listes: " + e.toString());
        }
        //    catch (ParseException e) {
        //       Log.d("*********************************", "Probleme lors de la conversion convertDateStringToDate : " + e.toString());
        // }
        return quizContents;
    }
}