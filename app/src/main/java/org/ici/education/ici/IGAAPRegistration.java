package org.ici.education.ici;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class IGAAPRegistration extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    EditText stdLastName;
    EditText stdFirstName;
    EditText homeroomTeacher;
    Spinner schoolSpin;
    EditText stdGender;
    EditText stdAge;
    Spinner ethnicity;
    EditText homeaddress;
    EditText city;
    EditText parentsLastName;
    EditText parentsFirstName;
    EditText phonenumber;
    EditText emailaddress;
    EditText signature;
    EditText zipcode;
    EditText date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_igaapregistration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Shared Preferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        // Form information
        stdLastName = (EditText) findViewById(R.id.stdLastNameEdt);
        stdFirstName = (EditText) findViewById(R.id.stdFirstNameEdt);
        homeroomTeacher = (EditText) findViewById(R.id.teacherNameEdt);
        schoolSpin = (Spinner) findViewById(R.id.schoolSpin);
        stdGender = (EditText) findViewById(R.id.genderEdt);
        stdAge = (EditText) findViewById(R.id.ageEdt);
        ethnicity = (Spinner) findViewById(R.id.ethnicitySpin);
        homeaddress = (EditText) findViewById(R.id.homeaddressEdt);
        city = (EditText) findViewById(R.id.cityEdt);
        zipcode = (EditText) findViewById(R.id.zipcodeEdt);
        parentsLastName = (EditText) findViewById(R.id.parentsLastNameEdt);
        parentsFirstName = (EditText) findViewById(R.id.parentsFirstNameEdt);
        phonenumber = (EditText) findViewById(R.id.phonenumberEdt);
        emailaddress = (EditText) findViewById(R.id.emailaddressEdt);
        signature = (EditText) findViewById(R.id.signatureEdt);
        date = (EditText) findViewById(R.id.dateEdt);


        // Submit button
        Button submit = (Button) findViewById(R.id.submitIgaapBtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadSharedPreferences();
                startActivity(new Intent(IGAAPRegistration.this, MainActivity.class));
            }
        });
    }

    private void loadSharedPreferences()
    {
        editor.putString("stdlastname", stdLastName.getText().toString());
        editor.putString("stdfirstname", stdFirstName.getText().toString());
        editor.putString("homeroomteacher", homeroomTeacher.getText().toString());
        editor.putString("stdschool", schoolSpin.getSelectedItem().toString()); // SchoolSpin
        editor.putString("stdgender", stdGender.getText().toString());
        editor.putString("stdage", stdAge.getText().toString());
        editor.putString("ethnicity", ethnicity.getSelectedItem().toString()); // EthnicitySpin
        editor.putString("homeaddress", homeaddress.getText().toString());
        editor.putString("city", city.getText().toString());
        editor.putString("zipcode", zipcode.getText().toString());
        editor.putString("parentslastname", parentsLastName.getText().toString());
        editor.putString("parentsfirstname", parentsFirstName.getText().toString());
        editor.putString("phonenumber", phonenumber.getText().toString());
        editor.putString("emailaddress", emailaddress.getText().toString());
        editor.putString("signature", signature.getText().toString());
        editor.putString("date", date.getText().toString());

    }

}
