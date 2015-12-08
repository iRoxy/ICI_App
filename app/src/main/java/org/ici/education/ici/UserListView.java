package org.ici.education.ici;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class UserListView extends ListActivity {
    public ArrayAdapter<String> arrayAdapter;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private ListView listView;
    private ArrayList<String> listOfUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        listView = (ListView) findViewById(android.R.id.list);
        listOfUsers = new ArrayList<String>();

        // Send To Server Button
        Button sendToServerBtn = (Button) findViewById(R.id.sendServerBtn);
        sendToServerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Server Code Here
            }
        });



        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPref.edit();
        for(int i = 0; i < sharedPref.getInt("numUsers", 0); i++)
        {

            String userName = sharedPref.getString("user" + i, "default") + " "
                    + sharedPref.getString("userLName" + i, "default");

            listOfUsers.add(userName);
        }

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOfUsers);
        listView.setAdapter(arrayAdapter);
    }


}
