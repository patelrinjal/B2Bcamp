package com.example.b2bcamp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.b2bcamp.Utility.AllSharedPrefernces;

public class splash extends AppCompatActivity {

    AllSharedPrefernces allSharedPrefernces = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        allSharedPrefernces = new AllSharedPrefernces(this);

        if (allSharedPrefernces.isUserLogin()) {

            if (allSharedPrefernces.getUserType().equalsIgnoreCase("seller")) {

                Intent i = new Intent(splash.this,Customerhomepage.class);
                startActivity(i);

                finishAffinity();

            }
            else  if (allSharedPrefernces.getUserType().equalsIgnoreCase("user")) {

                Intent i = new Intent(splash.this,Sellerhomepage.class);
                startActivity(i);

                finishAffinity();

            }

        }

    }

    public void ClickonLogin(View view) {
        Intent i=new Intent(splash.this,Userselection.class);
        i.putExtra("from","login");
        startActivity(i);
    }

    public void ClickonSignup(View view) {
        Intent i=new Intent(splash.this,Userselection.class);
        i.putExtra("from","signup");
        startActivity(i);
    }

}
