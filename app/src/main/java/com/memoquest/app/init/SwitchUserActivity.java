package com.memoquest.app.init;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.memoquest.app.R;
import com.memoquest.app.modal.ModalMessages;
import com.memoquest.model.db.User;
import com.memoquest.service.entity.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwitchUserActivity extends Activity implements View.OnClickListener {

    private UserService userService;
    private ModalMessages modalMessages;
    private TextView newUseText;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_user);

        userService = new UserService();

        newUseText = (TextView) this.findViewById(R.id.newUseText);
        newUseText.setOnClickListener(this);

        initActivity();
    }

    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.newUseText:
                Intent intent = new Intent(SwitchUserActivity.this, ConnectionActivity.class);
                startActivity(intent);
            break;
            default:
                modalMessages.showWrongMessage(this, "Probleme Technique", this.getClass().getSimpleName() + "Methode: onClick():" + "Switch default.....");
            break;
        }
    }

    private void initActivity(){
        users = userService.getAll();

        if(users.isEmpty()){

            Intent intentMenu = new Intent(SwitchUserActivity.this, ConnectionActivity.class);
            startActivity(intentMenu);

        }
        else if(users.size() == 1 && users.get(0).getActive() == 1){

            Intent intentMenu = new Intent(SwitchUserActivity.this, MenuActivity.class);
            startActivity(intentMenu);

        }else {

            showUserListview();
        }
    }

    private void showUserListview(){
        final ListView listView = (ListView) findViewById(R.id.userListview);
        String[] values = getUserLoginListValues();

        final List<String> list = new ArrayList<String>();

        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }

        final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    User user = users.get(position);

                    userService.setUserToActive(user);

                    Intent intentMenu = new Intent(SwitchUserActivity.this, MenuActivity.class);
                    startActivity(intentMenu);

            }
        });
    }


    private String[] getUserLoginListValues(){
        List<String> loginList = new ArrayList<String>();

        for(User user : users){
            loginList.add(user.getLogin())      ;
        }

        return loginList.toArray(new String[0]);
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        Map<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

}