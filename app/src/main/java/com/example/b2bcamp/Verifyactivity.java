package com.example.b2bcamp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.b2bcamp.Utility.Commonfunctions;
import com.example.b2bcamp.Utility.Constants;

import java.util.HashMap;

public class Verifyactivity extends AppCompatActivity {
    PinView Pin_View;
    Button btn_verify;

    String code , id , type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifyactivity);
        Pin_View=(PinView) findViewById(R.id.Pin_View);
        btn_verify=(Button) findViewById(R.id.btn_verify);

        code = getIntent().getStringExtra("otp");
        id = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");


        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Commonfunctions.checkstring(Pin_View.getText().toString()))
                {
                    Pin_View.setError("Please enter your name");
                    return;
                }

                if (code.equalsIgnoreCase(Pin_View.getText().toString())) {

                    Intent i = new Intent(Verifyactivity.this,Resetactivity.class);
                    i.putExtra("id",id);
                    i.putExtra("type",type);
                    startActivity(i);

                }
                else {
                    Toast.makeText(Verifyactivity.this, "Invalid OTP. Please Enter again.", Toast.LENGTH_LONG).show();
                }






            }
            });
        }

    }







