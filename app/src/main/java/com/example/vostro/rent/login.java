package com.example.vostro.rent;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class login extends Activity
{
    EditText Email, Password, Mobile;
    String email, password, mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email = (EditText)findViewById(R.id.et_email);
        Password = (EditText)findViewById(R.id.et_password);
        Mobile = (EditText)findViewById(R.id.et_mobile);
    }

    public void saveInfo(View view)
    {
        email = Email.getText().toString();
        password = Password.getText().toString();
        mobile = Mobile.getText().toString();
        BackgroundTask backgroundTask = new BackgroundTask();
        backgroundTask.execute(email, password, mobile);
        finish();

    }
    class BackgroundTask extends AsyncTask<String, Void, String>
    {
        String add_info_url;

        @Override
        protected void onPreExecute()
        {
            add_info_url = "http://kothakhalicha.000webhostapp.com/add_info.php";

        }

        @Override
        protected String doInBackground(String ... args)
        {
            String email, password, mobile;
            email= args[0];
            password= args[1];
            mobile= args[2];
            try
            {
                URL url = new URL (add_info_url);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data_string = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email, "UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password, "UTF-8")+"&"+
                        URLEncoder.encode("mobile","UTF-8")+"="+URLEncoder.encode(mobile, "UTF-8");

                bufferedWriter.write(data_string);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                httpURLConnection.disconnect();
                return "One Row data Inserted";



            }



            catch (MalformedURLException e)
            {
                e.printStackTrace();

            } catch (IOException e)
            {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values)
        {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String  result)
        {
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }
    }



}
