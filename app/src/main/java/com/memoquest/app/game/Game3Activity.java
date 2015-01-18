package com.memoquest.app.game;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.memoquest.app.R;
import com.memoquest.app.modal.ModalMessages;
import com.memoquest.model.db.QuizContent;
import com.memoquest.service.entity.QuizContentService;

public class Game3Activity extends ActionBarActivity implements View.OnClickListener ,TextToSpeech.OnInitListener{

    private QuizContent quizContent;
    private int errorNumber;
    private EditText editResponseGame3;
    private TextView validerText;
    private QuizContentService quizContentService;
    private TextToSpeech textToSpeech;
    private static final int MY_TEXT_TO_SPEECH_CHECK_CODE = 13;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);

        quizContentService = new QuizContentService();
        errorNumber = 0;
        editResponseGame3 = (EditText) this.findViewById(R.id.edit_response_game3);

        validerText = (TextView) this.findViewById(R.id.validerText);
        validerText.setOnClickListener(this);

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
        textToSpeech.speak("Tu dois ecrire la phrase en mettant les mots dans l'ordre", TextToSpeech.QUEUE_FLUSH, null);
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

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == MY_TEXT_TO_SPEECH_CHECK_CODE)
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
    protected void onStart() {
        super.onStart();

        TextView textViewQuestion = (TextView) findViewById(R.id.textViewQuestion);
        if(quizContent.getQuestion() != null){
            textViewQuestion.setText(quizContent.getQuestion());
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.validerText:
                String answer = String.valueOf(editResponseGame3.getText());
                answer = answer.toLowerCase().trim();
                checkAnswer(answer);
                break;

            default:
                ModalMessages.showWrongMessage(this, "Probleme Technique", this.getClass().getSimpleName() + "Methode: onClick():" + "Switch default.....");
                break;
        }
    }

    public void checkAnswer(String answerGamer){

        if(answerGamer.equals(quizContent.getSolution().toLowerCase())){
            Toast.makeText(getApplicationContext(), "Bonne réponse", Toast.LENGTH_LONG).show();
            Intent returnIntent = new Intent();
            returnIntent.putExtra("errorNumber", errorNumber);
            setResult(RESULT_OK, returnIntent);
            finish();
        }
        else{
            errorNumber++;
            textToSpeech.speak("Mauvaise réponse", TextToSpeech.QUEUE_FLUSH, null);
        }
    }
}