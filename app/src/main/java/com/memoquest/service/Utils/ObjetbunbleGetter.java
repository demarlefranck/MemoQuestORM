package com.memoquest.service.Utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.memoquest.app.modal.ModalMessages;

/**
 * Created by franck on 15/01/15.
 */
public class ObjetbunbleGetter {

    public long getLongObjetbunbleValue(Context context, Intent intent, String bundleKey ){

        long objetbunbleValue = -1;

        Bundle objetbunble = intent.getExtras();

        if (objetbunble != null && objetbunble.containsKey(bundleKey)){

            objetbunbleValue = intent.getLongExtra(bundleKey, -1);

        } else{
            ModalMessages.showWrongMessage(context, "Probleme Technique", this.getClass().getSimpleName() + "onCreate(): " + "Probleme d'identification de ObjetbunbleValue");
        }
        return objetbunbleValue;
    }

    public int getIntObjetbunbleValue(Context context, Intent intent, String bundleKey ){
        int objetbunbleValue = -1;

        Bundle objetbunble = intent.getExtras();

        if (objetbunble != null && objetbunble.containsKey(bundleKey)){

            objetbunbleValue = intent.getIntExtra(bundleKey, -1);

        } else{
            ModalMessages.showWrongMessage(context, "Probleme Technique", this.getClass().getSimpleName() + "onCreate(): " + "Probleme d'identification de ObjetbunbleValue");
        }
        return objetbunbleValue;
    }
}
