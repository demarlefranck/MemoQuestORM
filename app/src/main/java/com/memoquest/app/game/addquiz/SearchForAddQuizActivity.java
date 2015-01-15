package com.memoquest.app.game.addquiz;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.memoquest.app.R;
import com.memoquest.app.modal.ModalMessages;
import com.memoquest.model.GlobalQuiz;
import com.memoquest.model.db.Quiz;
import com.memoquest.service.GlobalQuizService;
import com.memoquest.service.Utils.ObjetbunbleGetter;
import com.memoquest.service.entity.QuizService;
import com.memoquest.service.rest.GlobalQuizRestService;
import com.memoquest.service.rest.QuizRestService;

import java.util.ArrayList;
import java.util.List;

public class SearchForAddQuizActivity extends ListActivity {

   private ObjetbunbleGetter objetbunbleGetter;
   private List<Quiz> quizs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        objetbunbleGetter = new ObjetbunbleGetter();

        int quizFilter = objetbunbleGetter.getIntObjetbunbleValue(this, this.getIntent(), "filter");
        getServerQuiz(quizFilter);

        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_quiz, getQuizNameListValues()));

        ListView listView = getListView();
        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Quiz quiz = quizs.get(position);
            saveQuiz(quiz);

            }
        });
    }

    private void getServerQuiz(int quizFilter){

        QuizRestService quizRestService = new QuizRestService();

        if(quizFilter != -1){
            switch (quizFilter) {
                case 0:
                    quizs = quizRestService.getAllQuizsServer();
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

    private void saveQuiz(final Quiz quiz){


        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Confirmation"); //Set Alert dialog title here
        alert.setMessage("Sauvegarde du quiz ?"); //Message here

        alert.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            QuizService quizService = new QuizService();

            //  recuperation du quiz complet + sauvegarde dans la base
            if(quizService.findUniqueByServeurId(quiz.getServerId()) == null){

                GlobalQuizRestService globalQuizRestService = new GlobalQuizRestService();
                GlobalQuizService globalQuizService = new GlobalQuizService();

                GlobalQuiz globalQuiz = globalQuizRestService.getGlobalQuizRest(quiz);
                globalQuizService.editGlobalQuiz(globalQuiz);
            }

            Toast.makeText(getApplicationContext(), "Sauvegarde OK", Toast.LENGTH_LONG).show();
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
