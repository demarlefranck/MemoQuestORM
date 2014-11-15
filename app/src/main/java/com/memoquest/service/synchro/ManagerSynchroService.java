package com.memoquest.service.synchro;

import android.content.Context;

import com.memoquest.service.ConnexionService;

/**
 * Created by franck on 15/11/14.
 */
public class ManagerSynchroService {

    private ConnexionService connexionService;

    public void updateAllData(Context context) {
        connexionService = new ConnexionService();

        if(connexionService.isConnected(context)){

            /*
            ListeSynchroService listeSynchroService = new ListeSynchroService(context);
            MotDefSynchroService motDefSynchroService = new MotDefSynchroService(context);

            listeSynchroService.updateListe();
            motDefSynchroService.updateMotDef();
*/
        }
    }
}
