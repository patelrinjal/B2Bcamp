package com.example.b2bcamp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.b2bcamp.Utility.DataInterface;
import com.example.b2bcamp.Utility.Webservice_Volley;

public class Userselection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userselection);



    }

    public void ClickonUser(View view) {

        Intent i=new Intent(Userselection.this,Userlogin.class);
        startActivity(i);
    }

    public void ClickonSeller(View view) {
        Intent i=new Intent(Userselection.this,Sellerlogin.class);
        startActivity(i);
    }
}
