package com.example.b2bcamp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.b2bcamp.Utility.Commonfunctions;
import com.example.b2bcamp.Utility.Constants;

import java.util.HashMap;

public class Resetactivity extends AppCompatActivity {
    EditText edt_password,edt_repassword;
    Button btn_reset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetactivity);
        edt_password=(EditText)findViewById(R.id.edt_password);
        edt_repassword=(EditText)findViewById(R.id.edt_repassword);
        btn_reset = (Button) findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Commonfunctions.checkPassword(edt_password.getText().toString())) {
                    edt_password.setError("Please enter valid password");
                    return;
                }

                if (!Commonfunctions.checkPassword(edt_repassword.getText().toString())) {
                    edt_repassword.setError("Reenter valid password");
                    return;
                }
                if (edt_password.getText().toString().equals(edt_repassword.getText().toString())) {
                    edt_repassword.setError("Both password not match");
                    return;
                }
                Intent i=new Intent(Resetactivity.this,Userlogin.class);
                startActivity(i);



            }

            });
        }
        }




