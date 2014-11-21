package com.memoquest.app.game;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.memoquest.app.R;
import com.memoquest.app.modal.ModalMessages;
import com.memoquest.model.db.Quiz;
import com.memoquest.service.entity.QuizService;

import java.util.ArrayList;
import java.util.List;

public class QuizChoiceActivity extends ListActivity {


    static final String[] FRUITS = new String[] { "Apple", "Avocado", "Banana",
            "Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit",
            "Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple" };


    private QuizService quizService;
    private List<Quiz> quizs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        quizService = new QuizService();

        initActivity();


//        setContentView(R.layout.activity_quiz_choice);



  //      setListAdapter(new ArrayAdapter<String>(this, R.layout.list_fruit,FRUITS));

        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_fruit, getQuizNameListValues()));

        ListView listView = getListView();
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                Quiz quiz = quizs.get(position);

                lunchGlobalQuizActivity(quiz.getId());

            }
        });



    }



    private void initActivity(){
        quizs = quizService.getAll();

        if(quizs.isEmpty()){

            ModalMessages.showWrongMessage(this, "Information", "aucune liste n'est présente, vous devez préalablement ajouter des listes");

        }
        else if(quizs.size() == 1){

            lunchGlobalQuizActivity(quizs.get(0).getId());

        }
    }

    private void lunchGlobalQuizActivity(long quizId) {
        Intent intent = new Intent(this, GlobalQuizActivity.class);
        intent.putExtra("quizId", quizId);
        startActivity(intent);
    }

    private String[] getQuizNameListValues(){
        List<String> quizNameList = new ArrayList<String>();

        for(Quiz quiz : quizs){
            quizNameList.add(quiz.getName());
        }

        return quizNameList.toArray(new String[0]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game_choice, menu);
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