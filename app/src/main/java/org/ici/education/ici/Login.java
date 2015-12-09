package org.ici.education.ici;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context caller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        caller = getApplicationContext();

        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Shared Preferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        // Username fields
        TextView usernameTxt = (TextView)findViewById(R.id.usernameTxtView);
        final EditText usernameEdt = (EditText) findViewById(R.id.usernameTxtEdit);

        // Password fields
        TextView passwordTxt = (TextView) findViewById(R.id.passwordTxtView);
        final EditText passwordEdt = (EditText) findViewById(R.id.passwordTxtEdit);

        // TODO: AUTHENTICATION? NAH...but will check if fields are not empty

        // login button and listener
        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameStr = usernameEdt.getText().toString();
                editor.putString("username", usernameStr);

                String passwordStr = passwordEdt.getText().toString();
                editor.putString("password", passwordStr);

                startActivity(new Intent(Login.this, Registration.class));

            }
        });
    }

}
