package com.memoquest.app.init;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.memoquest.app.R;
import com.memoquest.app.modal.ModalMessages;
import com.memoquest.model.db.Quiz;
import com.memoquest.model.db.QuizContent;
import com.memoquest.model.db.User;
import com.memoquest.service.ConnexionService;
import com.memoquest.service.entity.QuizContentService;
import com.memoquest.service.entity.QuizService;
import com.memoquest.service.entity.UserService;
import com.memoquest.service.rest.QuizContentRestService;
import com.memoquest.service.synchro.ManagerSynchroService;
import com.test.memoquest.model.QuizContentTest;
import com.test.memoquest.model.QuizTest;

public class MainActivity extends ActionBarActivity {

    private ConnexionService connexionService;
    private UserService userService;
    private ManagerSynchroService managerSynchroService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connexionService = new ConnexionService();
        userService = new UserService();
        managerSynchroService = new ManagerSynchroService();

       //   testQuizContent();
    }

    @Override
    protected void onStart() {

        Log.i("", "MainActivity.class: onStart()");
        super.onStart();



        /*
            section pour les tests

       // ServerQuizContent.txt();

       // testInsert();

//        testRestServer();




        QuizService quizService = new QuizService();
        if(quizService.getAll().size() == 0){
            insertSampleData();
        }

          fin de section pour les tests

 */
        if(userService.getAll().size() == 0){
            insertSampleDataUser();
        }





        /*
            Version OK

        */
        if(connexionService.isConnected(this)){

            startWithConnection();

        } else{

            startWithoutConnection();
        }

    }





    private void testRestServer() {

        QuizContentRestService quizContentRestService = new QuizContentRestService();

        Quiz quiz = new Quiz();
        quiz.setServerId(1);

         quizContentRestService.getQuizContentsByQuiz(quiz).size();
    }









/*
    private void testRestFake() {
        SkillRestService skillRestService = new SkillRestService();
        QuizRestService quizRestService = new QuizRestService();
        QuizContentRestService quizContentRestService = new QuizContentRestService();

        for (Skill skill : skillRestService.getServerSkills()){
            Log.d("getServerSkills:  ", skill.toString());
        }
        for (Quiz quiz : quizRestService.getAllQuizsServer()){
            Log.d("getQuizs:  ", quiz.toString());
        }
        for (QuizContent quizContent : quizContentRestService.getQuizContents()){
            Log.d("getQuizContents:  ", quizContent.toString());
        }


    }
*/


    private void testInsert() {


/*
        JsonReader jsonReader = new JsonReader();
        BddManager bddManager = new BddManager();
        bddManager.initBddTemp();

        Log.d("DEBUBQuizGetAll", new Select().from(ServerQuizContent.class).execute().toString());
        Log.d("DEBUBQuizGetAll", new Select().from(ServerQuiz.class).execute().toString());
        Log.d("DEBUBQuizGetAll", new Select().from(ServerSkill.class).execute().toString());
*/
    }

    private void insertSampleDataUser() {

        User user1 = connexionService.isAuthentifiate("demarl_f", "eip");
        userService.edit(user1, (long) -1);

        User user2 = connexionService.isAuthentifiate("dupe_j", "eip");
        userService.edit(user2, (long) -1);

        User user3 = connexionService.isAuthentifiate("chave_k", "eip");
        userService.edit(user3, (long) -1);

        User user4 = connexionService.isAuthentifiate("fourni_c", "eip");
        userService.edit(user4, (long) -1);

        User user5 = connexionService.isAuthentifiate("grosje_s", "eip");
        userService.edit(user5, (long) -1);

        User user6 = connexionService.isAuthentifiate("devill_b", "eip");
        userService.edit(user6, (long) -1);


    }
    private void insertSampleDataQuiz() {

        QuizService quizService = new QuizService();
        QuizContentService quizContentService = new QuizContentService();
        QuizTest quizTest = new QuizTest();
        QuizContentTest quizContentTest = new QuizContentTest();

        for(int i = 0; i != 10; i++){

            Quiz quiz = quizTest.createOneQuiz(i);

            quizService.edit(quiz, (long) -10);


            for(int j = 0; j != 5; j++){

                QuizContent quizContent = quizContentTest.createOneQuizContent(j, quiz);



                quizContentService.edit(quizContent, (long) -10);



            }
        }
    }

    private void test() {


/*
        Intent intentConnexion = new Intent(MainActivity.this, Game1ActivityOLD.class);
        startActivity(intentConnexion);
  */


        User userCurrent = userService.getUserActive();



        if(userCurrent == null){
            ModalMessages.showWrongMessage(this, "TEST", "Pas de user acif");
        }
        else{
            ModalMessages.showGoodMessage(this, "TEST", "User actif trouve");
        }
    }


    public void startWithConnection(){

      //  modalMessages.showGoodMessage(this, "Good", "le téléphone est connecté");

        User userCurrent = userService.getUserActive();

        if(userCurrent == null){

            Intent intentConnexion = new Intent(MainActivity.this, SwitchUserActivity.class);
            startActivity(intentConnexion);
        }
        else{

            //lancement des synchro donnees
            managerSynchroService.updateAllData(this);

            Intent intentMenu = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intentMenu);
        }
    }

    public void startWithoutConnection() {

        User userCurrent = userService.getUserActive();

        if(userCurrent == null){
            ModalMessages.showWrongMessage(this, "Probleme de connexion", "Une connexion internet est requise car vous n'êtes pas authentifié");
        }
        else{
            Intent intentMenu = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intentMenu);
        }
    }



    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}




















/*
    private void testQuizContent() {


        GlobalQuizService buisnessLayer = new GlobalQuizService();

        QuizService quizService = new QuizService();

        QuizContentTest quizContentTest = new QuizContentTest();


        QuizTest quizTest = new QuizTest();

        Quiz quiz = quizTest.createOneQuiz(1);

        QuizContent quizContent1 = quizContentTest.createOneQuizContent(1, quiz);
        QuizContent quizContent2 = quizContentTest.createOneQuizContent(2, quiz);
        QuizContent quizContent3 = quizContentTest.createOneQuizContent(3, quiz);

        List<QuizContent> quizContents = new ArrayList<QuizContent>();
        quizContents.add(quizContent1);
        quizContents.add(quizContent2);
        quizContents.add(quizContent3);

        GlobalQuiz globalQuizExpected = new GlobalQuiz();
        globalQuizExpected.setQuiz(quiz);
        globalQuizExpected.setQuizContents(quizContents);


        Log.d("DEBUBQuizGetAll", quizService.getAll().toString());

        buisnessLayer.editGlobalQuiz(globalQuizExpected);
        GlobalQuiz globalQuizResult = buisnessLayer.findGlobalQuiz(quiz);


        Log.d("DEBUBQuizGetAll", quizService.getAll().toString());

        for (QuizContent quizContentItem : globalQuizResult.getQuizContents()) {

            Log.d("DEBUB", quizContentItem.toString());
        }

        buisnessLayer.deleteGlobalQuiz(globalQuizExpected);
        GlobalQuiz globalQuizResult2 = buisnessLayer.findGlobalQuiz(quiz);

        for (QuizContent quizContentItem : globalQuizResult2.getQuizContents()) {

            Log.d("DEBUB", quizContentItem.toString());
        }

        quiz.delete();

        Log.d("DEBUBQuiz", quiz.toString());


        Log.d("DEBUBQuizGetAll", quizService.getAll().toString());
    }
*/