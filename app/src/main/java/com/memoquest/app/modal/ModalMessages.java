package com.memoquest.app.modal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.memoquest.app.R;

/**
 * Created by franck on 15/11/14.
 */
public class ModalMessages {


    public void showGoodAnswerMessage(Context context){
/*
        Toast.makeText(context,
                "Bien jouer", Toast.LENGTH_LONG).show();
  */
        new AlertDialog.Builder(context)
                .setTitle("Bien jouer")
                .setMessage("Bonne réponse")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //close
                    }
                })
                .setIcon(R.drawable.ok_icon)
                .show();
    }

    public void showWrongAnswerMessage(Context context) {
        new AlertDialog.Builder(context)
                .setTitle("Désolé")
                .setMessage("Mauvaise réponse")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //close
                    }
                })
                .setIcon(R.drawable.not_ok_icon)
                .show();
    }
}
