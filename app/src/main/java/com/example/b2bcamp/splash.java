package com.example.b2bcamp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    public void ClickonLogin(View view) {
        Intent i=new Intent(splash.this,Userlogin.class);
        startActivity(i);
    }

    public void ClickonSignup(View view) {
        Intent i=new Intent(splash.this,Usersignup.class);
        startActivity(i);}

}
