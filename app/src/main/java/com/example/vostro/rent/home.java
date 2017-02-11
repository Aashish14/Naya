package com.example.vostro.rent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class home extends Activity {
Button B1;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        B1 = (Button)findViewById(R.id.addbutton);
        textView = (TextView)findViewById(R.id.textView);

        //network connection
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected())
        {
            textView.setVisibility(View.INVISIBLE);
        }
        else
        {
            B1.setEnabled(false);
        }

    }


    public void addcontact(View view)
    {

    startActivity(new Intent(this, login.class));


    }
}
