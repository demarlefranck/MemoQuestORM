package com.memoquest.dao.rest;

import android.os.AsyncTask;
import android.util.Log;

import com.memoquest.model.db.Quiz;
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

public class RestGetQuizDao extends AsyncTask<Void, Void, List<Quiz> > {

    private int userId;

    public void setUserId(int id) {
        this.userId = id;
    }

    protected List<Quiz> doInBackground(Void... params) {

        List<Quiz> quizs = null;

        final String url = "http://memoquest.fr/MemoQuest/app_dev.php/api/quizzes";

        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);

        try {
            HttpResponse httpResponse = httpclient.execute(httpGet);
            int statusCode = httpResponse.getStatusLine().getStatusCode();

            if (statusCode == 200) {

                quizs = httpEntityToObject(httpResponse.getEntity());


                Log.e("INFO", "statusCode:  "  + String.valueOf(statusCode));

            } else {
               // new TechnicalAppException("RestGetListesDaoTest.class: Probleme lors de la recuperation des listes");
            }
        } catch (Exception e) {

            e.toString();
        }


        Log.d("debug         quizs.toString:        ", quizs.toString());

        return quizs;
    }

    public List<Quiz> httpEntityToObject(HttpEntity httpEntity){

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



       return stringToQuizs(builder.toString());

    }


    public List<Quiz> stringToQuizs(String string){

        MyDateUtils myDateUtils = new MyDateUtils();
        List<Quiz> quizs = new ArrayList<Quiz>();

        try {
            JSONArray listesJson = new JSONArray(string);
            Quiz quiz;




            for (int i = 0; i < listesJson.length(); i++) {

                JSONObject jsonObject = listesJson.getJSONObject(i);

                quiz = new Quiz();
                quiz.setServerId(parseInt(jsonObject.getString("id")));
                quiz.setName(jsonObject.getString("name"));

                /*
                    pb avec le format de bry 2014-10-28T10:25:00+0100
                 */

                //      quiz.setCreateTime(myDateUtils.convertDateStringToDate(jsonObject.getString("created")));
                quiz.setCreateUser((long) parseInt(jsonObject.getString("created_by")));
                //    quiz.setUpdateTime(myDateUtils.convertDateStringToDate(jsonObject.getString("updated")));
                quiz.setUpdateUser((long) parseInt(jsonObject.getString("updated_by")));
                quizs.add(quiz);
            }
        }
        catch (JSONException e) {
            Log.d("*********************************", "Probleme lors de la conversion Json to listes: " + e.toString());
        }
        //    catch (ParseException e) {
        //       Log.d("*********************************", "Probleme lors de la conversion convertDateStringToDate : " + e.toString());
        // }
        return quizs;
    }
}