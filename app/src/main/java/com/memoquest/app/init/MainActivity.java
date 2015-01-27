package com.memoquest.app.init;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.memoquest.app.R;
import com.memoquest.app.modal.ModalMessages;
import com.memoquest.model.db.User;
import com.memoquest.service.ConnexionService;
import com.memoquest.service.entity.UserService;
import com.memoquest.service.synchro.ManagerSynchroService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity  extends Activity implements View.OnClickListener {

    private ConnexionService connexionService;
    private UserService userService;
    private ManagerSynchroService managerSynchroService;
    private TextView newUseText;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connexionService = new ConnexionService();
        userService = new UserService();
        managerSynchroService = new ManagerSynchroService();

        userService = new UserService();
        newUseText = (TextView) this.findViewById(R.id.newUseText);
        newUseText.setOnClickListener(this);

        initActivity();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(userService.getAll().size() == 0){
            insertSampleDataUser();
        }

        if(connexionService.isConnected(this)){

            startWithConnection();

        } else {

            startWithoutConnection();
        }
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

    public void startWithConnection(){

        User userCurrent = userService.getUserActive();

        if(userCurrent == null){


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

    private void initActivity(){
        users = userService.getAll();

        if(users.isEmpty()){

            Intent intentMenu = new Intent(MainActivity.this, ConnectionActivity.class);
            startActivity(intentMenu);

        }
        else {

            showUserListview();
        }
    }

    private void showUserListview(){
        final ListView listView = (ListView) findViewById(R.id.userListview);
        String[] values = getUserLoginListValues();

        final List<String> list = new ArrayList<String>();

        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }

        final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                User user = users.get(position);
                userService.editUserToActive(user);

                Intent intentMenu = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intentMenu);
            }
        });
    }


    private String[] getUserLoginListValues(){
        List<String> loginList = new ArrayList<String>();

        for(User user : users){
            loginList.add(user.getLogin())      ;
        }

        return loginList.toArray(new String[0]);
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        Map<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.newUseText:
                Intent intent = new Intent(MainActivity.this, ConnectionActivity.class);
                startActivity(intent);
                break;
            default:
                ModalMessages.showWrongMessage(this, "Probleme Technique", this.getClass().getSimpleName() + "Methode: onClick():" + "Switch default.....");
                break;
        }
    }
    
    
    /*
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
    */
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


/*

18/01/2015
requete SQL  `QUESTION_TYPE` =0:   semble bon quelque erreurs dans la base


requete SQL  `QUESTION_TYPE` =1:   semble bon quelque erreurs dans la base





requete SQL  `QUESTION_TYPE` =3:   semble Ok un seul exemple


SELECT q.`NAME`
FROM  `MQ_QUIZ` AS q,  `MQ_QUIZ_CONTENT` AS qc
WHERE qc.`QUIZ_ID` = q.`ROW_ID`
AND qc.`QUESTION_TYPE` =0
GROUP BY q.`NAME`
LIMIT 0 , 30






 */