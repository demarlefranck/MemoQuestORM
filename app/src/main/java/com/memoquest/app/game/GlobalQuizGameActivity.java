package com.memoquest.app.game;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.memoquest.app.R;
import com.memoquest.app.modal.ModalMessages;
import com.memoquest.model.GlobalQuiz;
import com.memoquest.model.db.Quiz;
import com.memoquest.model.db.QuizContent;
import com.memoquest.service.GlobalQuizService;
import com.memoquest.service.entity.QuizService;

import java.util.Calendar;

public class GlobalQuizGameActivity extends ActionBarActivity implements TextToSpeech.OnInitListener{
    private static final int REQUEST_SEARCH_CODE = 10;
    private static final int REQUEST_QUIZ_CONTENT_CODE = 11;
    private static final int REQUEST_GLOBAL_QUIZ_END_CODE = 12;
    private static final int MY_TEXT_TO_SPEECH_CHECK_CODE = 13;

    private Resources resources;
    private GlobalQuiz globalQuiz;
    private Calendar dateStart;
    private Calendar dateStop;
    private int errorNumber;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resources = getResources();
        errorNumber = 0;
        globalQuiz = null;

        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, MY_TEXT_TO_SPEECH_CHECK_CODE);
    }

    @Override
    public void onInit(int i) {
        searchQuizId();
    }


    private void searchQuizId() {

        QuizService quizService = new QuizService();
        if(quizService.findAll().isEmpty()){
            textToSpeech.speak("Aucun quouiz disponible, tu dois télécharger au moins un nouveau quouiz", TextToSpeech.QUEUE_FLUSH, null);
            Toast.makeText(getApplicationContext(),String.format(resources.getString(R.string.quiz_empty)) , Toast.LENGTH_LONG).show();
            Intent returnIntent = new Intent();
            setResult(RESULT_OK, returnIntent);
            finish();
        }
        else {
            textToSpeech.speak("Selectionne ton quouiz", TextToSpeech.QUEUE_FLUSH, null);
            Intent intent = new Intent(this, SearchForGameQuizActivity.class);
            intent.putExtra("filter", 0);
            startActivityForResult(intent, REQUEST_SEARCH_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_SEARCH_CODE) {

            if(resultCode == RESULT_OK){
                long quizId = data.getLongExtra("quizId", -1);

                if(quizId != -1){
                    startGlobalQuizGame(quizId);
                }
                else{
                    Toast.makeText(getApplicationContext(),"probleme de recuperation de quiz id, onActivityResult() GlobalQuizGameActivity.class " , Toast.LENGTH_LONG).show();
                }
            }
        }

        else if (requestCode == REQUEST_QUIZ_CONTENT_CODE) {

            if(resultCode == RESULT_OK){
                int errorNumberTemp = data.getIntExtra("errorNumber", -1);
                    //MARCHE PAS LA REPONSE MET TROP DE TEMPS A REVENIR
               //     Toast.makeText(getApplicationContext(), "errorNumberTemp: " + errorNumberTemp, Toast.LENGTH_LONG).show();
                if(errorNumberTemp != -1){
                    errorNumber = errorNumber + errorNumberTemp;
                }
                else{
                    Toast.makeText(getApplicationContext(),"probleme de recuperation de errorNumber, onActivityResult() GlobalQuizGameActivity.class " , Toast.LENGTH_LONG).show();
                }
            }
        }
        else if (requestCode == REQUEST_GLOBAL_QUIZ_END_CODE) {

            if(resultCode == RESULT_OK){


                dateStop = Calendar.getInstance();

                showResult();
            }
        }
        else if (requestCode == MY_TEXT_TO_SPEECH_CHECK_CODE)
        {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS)
            {
                textToSpeech = new TextToSpeech(this, this);
            }
            else
            {
                Intent installIntent = new Intent();
                installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
            }
        }
    }

    private void startGlobalQuizGame(long quizId) {

        QuizService guizService = new QuizService();
        GlobalQuizService globalQuizService = new GlobalQuizService();

        Quiz quiz = guizService.findUniqueById(quizId);
        globalQuiz = globalQuizService.findGlobalQuiz(quiz);

        lunchAllQuizContentGame();
     }

    private void lunchAllQuizContentGame() {

        dateStart = Calendar.getInstance();

        for(QuizContent quizContent : globalQuiz.getQuizContents()) {

            switch (quizContent.getQuestionType()) {
                case 0:
                    Intent intent0 = new Intent(this, Game0Activity.class);
                    intent0.putExtra("quizContentId", quizContent.getId());
                    startActivityForResult(intent0, REQUEST_QUIZ_CONTENT_CODE);
                break;

                case 1:
                    Intent intent1 = new Intent(this, Game1Activity.class);
                    intent1.putExtra("quizContentId", quizContent.getId());
                    startActivityForResult(intent1, REQUEST_QUIZ_CONTENT_CODE);
                break;

                case 2:
                    Intent intent2 = new Intent(this, Game2Activity.class);
                    intent2.putExtra("quizContentId", quizContent.getId());
                    startActivityForResult(intent2, REQUEST_QUIZ_CONTENT_CODE);
                break;

                case 3:
                    Intent intent3 = new Intent(this, Game3Activity.class);
                    intent3.putExtra("quizContentId", quizContent.getId());
                    startActivityForResult(intent3, REQUEST_QUIZ_CONTENT_CODE);
                    break;

                default:
                    ModalMessages.showWrongMessage(this, "Probleme Technique", "getQuestionType switch default quizContent.getQuestionType():" + quizContent.getQuestionType());
                break;
            }
        }

        Intent intent = new Intent(this, EndGlobalActivityActivity.class);
        startActivityForResult(intent,REQUEST_GLOBAL_QUIZ_END_CODE);

    }

    private void showResult() {
        setContentView(R.layout.activity_global_quiz_game);

        TextView textViewErrorResult = (TextView) findViewById(R.id.textViewErrorResult);

      //  String textErrorResult = "vous avez fait " + errorNumber + " erreur" + addS(errorNumber);
        String textErrorResult = "";

        textViewErrorResult.setText(textErrorResult);

        TextView textViewTimeResult = (TextView) findViewById(R.id.textViewTimeResult);
        Long diff = dateStop.getTimeInMillis() - dateStart.getTimeInMillis();
        int ms = diff.intValue();
        int s = (ms / 1000);
        int minutes = (s / 60);
        int secondes = (s % 60);

        String chrono = "chrono: ";

        if(minutes > 0){
            chrono += minutes + " minute" + addS(minutes) + " ";
        }
        if(secondes > 0){
            chrono += secondes +" seconde" + addS(secondes);
        }

        textViewTimeResult.setText(chrono);
        textToSpeech.speak("Bien joué", TextToSpeech.QUEUE_FLUSH, null);
    }

    private String addS(int nb){

        String result = null;

        if(nb > 1){
            result = "s";

        }else{
            result = "";
        }
        return result;
    }
}
