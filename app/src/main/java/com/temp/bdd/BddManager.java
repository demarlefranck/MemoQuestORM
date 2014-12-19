package com.temp.bdd;

import com.activeandroid.query.Select;
import com.temp.model.ServerQuiz;
import com.temp.model.ServerQuizContent;
import com.temp.model.ServerSkill;
import com.temp.reader.JsonReader;

import java.util.List;

/**
 * Created by franck on 18/12/14.
 */
public class BddManager {

    public void initBddTemp(){
        if(new Select().from(ServerSkill.class).execute().size() == 0){
            initBddTempData();
        }
    }

    private void initBddTempData() {

        JsonReader jsonReader = new JsonReader();

        List<ServerSkill> serverSkills = jsonReader.getServerSkills();
        for (ServerSkill serverSkill : serverSkills){
            serverSkill.save();
        }
        List<ServerQuiz> serverQuizs = jsonReader.getServerQuiz();
        for (ServerQuiz serverQuiz : serverQuizs){
            serverQuiz.save();
        }
        List<ServerQuizContent> serverQuizContents = jsonReader.getServerQuizContent();
        for (ServerQuizContent serverQuizContent : serverQuizContents){
            serverQuizContent.save();
        }
    }
}
