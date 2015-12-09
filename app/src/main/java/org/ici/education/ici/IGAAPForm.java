package org.ici.education.ici;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class IGAAPForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_igaapform);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Web initialization
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewContent());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.loadUrl("http://www.icieducates.org/index.php/" +
                "community-programs/icis-gwinnett-academic-assist-program-i-gaap/" +
                "icis-gwinnett-academic-assist-program-english/");

        //TODO: For Spanish
        /*
        webView.loadUrl("http://www.icieducates.org/index.php/community-programs/" +
                "icis-gwinnett-academic-assist-program-i-gaap/" +
                "icis-gwinnett-academic-assist-program-i-gaap-en-espanol/");
                */



        // Agree Button
        Button agreeBtn = (Button) findViewById(R.id.agreeBtn);
        agreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IGAAPForm.this, IGAAPRegistration.class));
            }
        });

        // Disagree Button
        Button disagreeBtn = (Button) findViewById(R.id.disagreeBtn);
        disagreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If they disagree, go back to home screen
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private class WebViewContent extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            view.loadUrl(url);
            return true;
        }
    }
}
