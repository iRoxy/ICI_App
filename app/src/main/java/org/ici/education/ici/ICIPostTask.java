package org.ici.education.ici;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

/**
 * Created by al2king on 12/8/2015.
 */
public class ICIPostTask extends AsyncTask<URL, String, String>
{

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    private static final String SINGLE_URL_ONLY = " only accepts a single URL";
    private static final String TAG = "ICIPost";
    private Context caller;
    private long start,elapsed = 0;
    String lineEnd = "\r\n";

    ICIPostTask(Context _ctx) {
        caller = _ctx;
        start = System.currentTimeMillis();
    }

    @Override
    protected String doInBackground(URL... urls) {

        sharedPref = PreferenceManager.getDefaultSharedPreferences(caller);
        editor = sharedPref.edit();

        HttpURLConnection conn = null;
        Scanner scanner = null;
        StringBuilder responseBody = new StringBuilder();

        try {
            // error if URLs != 1
            if (urls.length != 1)
                throw new IllegalArgumentException(this.getClass().getName() + SINGLE_URL_ONLY);
            // get the connection
            publishProgress("opening connection");

            DataOutputStream outputStream = null;

            conn = (HttpURLConnection) urls[0].openConnection();

            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            String username = sharedPref.getString("ELL_Username", "default");
            String fName = sharedPref.getString("ELL_fName", "");
            String mName = sharedPref.getString("ELL_mName", "");
            String lName = sharedPref.getString("ELL_lName", "");
            String email = sharedPref.getString("ELL_email", "");
            String pswd = sharedPref.getString("ELL_pswd", "");
            String level = sharedPref.getString("ELL_level", "");
            String notes = sharedPref.getString("ELL_notes", "");
            String county = sharedPref.getString("ELL_county", "");

            String sb = URLEncoder.encode("qsID", "UTF-8") + "=" + URLEncoder.encode("1415575623", "UTF-8")
                    + "&" + URLEncoder.encode("671", "UTF-8") + "=" + URLEncoder.encode(username,"UTF-8")
                    + "&" + URLEncoder.encode("672", "UTF-8") + "=" + URLEncoder.encode(fName, "UTF-8")
                    + "&" + URLEncoder.encode("673", "UTF-8") + "=" + URLEncoder.encode(mName, "UTF-8")
                    + "&" + URLEncoder.encode("674", "UTF-8") + "=" + URLEncoder.encode(lName, "UTF-8")
                    + "&" + URLEncoder.encode("675", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8")
                    + "&" + URLEncoder.encode("676", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8")
                    + "&" + URLEncoder.encode("677", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8")
                    + "&" + URLEncoder.encode("678", "UTF-8") + "=" + URLEncoder.encode(pswd, "UTF-8")
                    + "&" + URLEncoder.encode("679", "UTF-8") + "=" + URLEncoder.encode("ICI", "UTF-8")
                    + "&" + URLEncoder.encode("680", "UTF-8") + "=" + URLEncoder.encode(level, "UTF-8")
                    + "&" + URLEncoder.encode("681", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")
                    + "&" + URLEncoder.encode("682", "UTF-8") + "=" + URLEncoder.encode(notes, "UTF-8")
                    + "&" + URLEncoder.encode("683", "UTF-8") + "=" + URLEncoder.encode(county, "UTF-8");

            int totalLength = sb.length();
            conn.setFixedLengthStreamingMode(totalLength +2);

            outputStream = new DataOutputStream( conn.getOutputStream() );

            outputStream.writeBytes(sb);
            //outputStream.writeBytes("--");
            outputStream.close();
            conn.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                responseBody.append(line);
            }

            String msg = "(" + conn.getResponseCode() +  "):" + conn.getResponseMessage();
            Log.v(TAG, "Response" + msg);
            publishProgress(msg);
            // TODO handle non-200 errors here

        } catch (IOException e)
        {
            Log.e(TAG, e.getStackTrace().toString());
        }
        return responseBody.toString();
    }

    protected void onProgressUpdate(String status) {
        //webView.loadData("</html><body>Loading ...</body></html>", "text/html", null);
    }

    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String result) {
        // set the contents of TextView to the double retrieved in doInBackground

        Log.i(TAG, "result=" + result);

        // toast result!
        long elapsed = System.currentTimeMillis()-start;
        String msg;
        if (result.contains("Succes")) {
            msg = "Success: retrieved in " + elapsed + " milliseconds.";
        } else {
            msg = "Bummer: retrieved in " + elapsed + " milliseconds.";
        }
        Toast.makeText(caller, msg, Toast.LENGTH_LONG).show();
    }
}
