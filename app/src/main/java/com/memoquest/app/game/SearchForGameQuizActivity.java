package com.memoquest.app.game;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.memoquest.app.R;
import com.memoquest.app.modal.ModalMessages;
import com.memoquest.model.db.Quiz;
import com.memoquest.service.Utils.ObjetbunbleGetter;
import com.memoquest.service.entity.QuizService;

import java.util.ArrayList;
import java.util.List;

public class SearchForGameQuizActivity extends ListActivity {

    private ObjetbunbleGetter objetbunbleGetter;
    private List<Quiz> quizs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        objetbunbleGetter = new ObjetbunbleGetter();

        int quizFilter = objetbunbleGetter.getIntObjetbunbleValue(this, this.getIntent(), "filter");
        getQuiz(quizFilter);

        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_quiz, getQuizNameListValues()));

        ListView listView = getListView();
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Quiz quiz = quizs.get(position);
                LunchGame(quiz);

            }
        });
    }

    private void getQuiz(int quizFilter){

        QuizService quizService = new QuizService();

        if(quizFilter != -1){
            switch (quizFilter) {
                case 0:
                    quizs = quizService.getAll();
                    break;

                default:
                    ModalMessages.showWrongMessage(this, "indisponibilité", this.getClass().getSimpleName() + "onCreate(): " + "Switch default.....");
                    break;
            }
        }
        else{
            ModalMessages.showWrongMessage(this, "Probleme Technique", this.getClass().getSimpleName() + "onCreate(): " + "Probleme d'identification de ObjetbunbleValue  :  getObjetbunbleValue() = -1");
        }
    }

    private void LunchGame(final Quiz quiz){


        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Confirmation"); //Set Alert dialog title here
        alert.setMessage("C'est parti ? "); //Message here

        alert.setPositiveButton("Jouer", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("quizId", quiz.getId());
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });

        alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // close.
            }
        });

        AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }

    private String[] getQuizNameListValues(){
        List<String> quizNameList = new ArrayList<String>();

        for(Quiz quiz : quizs){
            quizNameList.add(quiz.getName());
        }

        return quizNameList.toArray(new String[0]);
    }
}
