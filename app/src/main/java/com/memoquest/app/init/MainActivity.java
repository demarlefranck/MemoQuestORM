package com.memoquest.app.init;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.memoquest.app.R;
import com.memoquest.app.modal.ModalMessages;
import com.memoquest.model.db.User;
import com.memoquest.service.ConnexionService;
import com.memoquest.service.entity.UserService;
import com.memoquest.service.synchro.ManagerSynchroService;

public class MainActivity extends ActionBarActivity {

    private ModalMessages modalMessages;
    private ConnexionService connexionService;
    private UserService userService;
    private ManagerSynchroService managerSynchroService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        modalMessages = new ModalMessages();
        connexionService = new ConnexionService();
        userService = new UserService();
        managerSynchroService = new ManagerSynchroService();

       //   testQuizContent();
    }

    @Override
    protected void onStart() {

        Log.i("", "MainActivity.class: onStart()");
        super.onStart();


        test();

        /*

         Version OK


        if(connexionService.isConnected(this)){

            startWithConnection();

        } else{

            startWithoutConnection();
        }

        */

    }

    private void test() {


/*
        Intent intentConnexion = new Intent(MainActivity.this, Game1Activity.class);
        startActivity(intentConnexion);
  */


        User user = new User();

        user.setActive(1);

        user.save();

        User userCurrent = userService.getUserActive();



        if(userCurrent == null){
            modalMessages.showWrongMessage(this, "TEST", "Pas de user acif");
        }
        else{
            modalMessages.showGoodMessage(this, "TEST", "User actif trouve");
        }




    }


    public void startWithConnection(){

        modalMessages.showGoodMessage(this, "Good", "le téléphone est connecté");

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
            modalMessages.showWrongMessage(this, "Probleme de connexion", "Une connexion internet est requise car vous n'êtes pas authentifié");
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


        BuisnessService buisnessLayer = new BuisnessService();

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