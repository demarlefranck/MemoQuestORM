package com.memoquest.app.modal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.memoquest.app.R;

/**
 * Created by franck on 15/11/14.
 */
public class ModalMessages {


    public static void showGoodMessage(Context context, String title, String message) {
/*
        Toast.makeText(context,
                "Bien jouer", Toast.LENGTH_LONG).show();
  */
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //close
                    }
                })
                .setIcon(R.drawable.ok_icon)
                .show();
    }

    public static void showWrongMessage(Context context, String title, String message) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //close
                    }
                })
                .setIcon(R.drawable.not_ok_icon)
                .show();
    }
}
