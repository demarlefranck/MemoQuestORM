package com.memoquest.app.game;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.memoquest.app.R;
import com.memoquest.app.modal.ModalMessages;
import com.memoquest.model.db.QuizContent;
import com.memoquest.service.entity.QuizContentService;

import java.util.List;

public class Game2Activity extends ActionBarActivity implements TextToSpeech.OnInitListener{

    private QuizContent quizContent;
    private int errorNumber;
    private QuizContentService quizContentService;
    private TextToSpeech textToSpeech;

    private static final int SPEECH_REQUEST_CODE = 1001;
    private static final int MY_TEXT_TO_SPEECH_CHECK_CODE = 13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_game2);

        quizContentService = new QuizContentService();
        errorNumber = 0;

        long quizContentId = getObjetbunbleValue();

        if (quizContentId != -1) {

            quizContent = quizContentService.findUniqueById(quizContentId);
        } else {
            ModalMessages.showWrongMessage(this, "Probleme Technique", this.getClass().getSimpleName() + "onCreate(): " + "Probleme d'identification de ObjetbunbleValue  :  getObjetbunbleValue() = -1");
        }

        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, MY_TEXT_TO_SPEECH_CHECK_CODE);
    }

    @Override
    public void onInit(int i) {
        TextView textViewQuestion = (TextView) findViewById(R.id.textViewQuestion);
        if(quizContent.getQuestion() != null){
            textViewQuestion.setText(quizContent.getQuestion());
        }
        textToSpeech.speak("Lis ce qui est écrit", TextToSpeech.QUEUE_FLUSH, null);
        displaySpeechRecognizer();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);


            Toast.makeText(getApplicationContext(), spokenText, Toast.LENGTH_LONG).show();
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

    private long getObjetbunbleValue() {

        long objetbunbleValue = -1;
        String bundleKey = "quizContentId";
        Bundle objetbunble = this.getIntent().getExtras();

        if (objetbunble != null && objetbunble.containsKey(bundleKey)) {

            objetbunbleValue = this.getIntent().getLongExtra(bundleKey, -1);

        } else {
            ModalMessages.showWrongMessage(this, "Probleme Technique", this.getClass().getSimpleName() + "onCreate(): " + "Probleme d'identification de ObjetbunbleValue");
        }
        return objetbunbleValue;
    }

    @Override
    public void onDestroy()
    {
        if (textToSpeech != null)
        {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

    private void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);


        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    public void checkAnswer(String answerGamer){

        if(answerGamer.equals(quizContent.getSolution())){
            Toast.makeText(getApplicationContext(), "Bonne réponse", Toast.LENGTH_LONG).show();
            Intent returnIntent = new Intent();
            returnIntent.putExtra("errorNumber", errorNumber);
            setResult(RESULT_OK, returnIntent);
            finish();
        }
        else{
            errorNumber++;
            Toast.makeText(getApplicationContext(), "Mauvaise réponse", Toast.LENGTH_LONG).show();
            textToSpeech.speak("Mauvaise réponse", TextToSpeech.QUEUE_FLUSH, null);
        }
    }
}