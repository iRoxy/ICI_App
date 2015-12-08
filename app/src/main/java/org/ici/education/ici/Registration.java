package org.ici.education.ici;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Registration extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        editor.putInt("numUsers", 0);
        editor.apply();


        // Register user button
        Button ellRegBtn = (Button) findViewById(R.id.ellRegBtn);
        ellRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                startActivityForResult(new Intent(Registration.this, ELLRegistration.class), 100);
            }
        });

        // View registered users button
        Button ellViewUserBtn = (Button) findViewById(R.id.ellViewUserBtn);
        ellViewUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.remove("numUsers");
                editor.apply();
                editor.putInt("numUsers", count);
                editor.commit();
                startActivity(new Intent(Registration.this, UserListView.class));

            }
        });

    }

}
