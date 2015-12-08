package org.ici.education.ici;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ELLRegistration extends FragmentActivity
{

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ellregistration);

        final EditText fnEdt = (EditText) findViewById(R.id.firstNameEdt);
        final EditText lnEdt = (EditText) findViewById(R.id.lastNameEdt);

        // Shared Preferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        Button submitBtn = (Button) findViewById(R.id.ellSubmitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fn = fnEdt.getText().toString();
                String ln = lnEdt.getText().toString();
                int key = sharedPreferences.getInt("numUsers", 900);
                editor.putString("user" + key, fn);
                editor.putString("userLName" + key, ln);
                editor.commit();

                finish();
            }
        });



    }


    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


}
