package com.example.b2bcamp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.b2bcamp.Utility.Commonfunctions;

public class Verifyactivity extends AppCompatActivity {
    EditText edt_num1,edt_num2,edt_num3,edt_num4,edt_num5,edt_num6;
    Button btn_verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifyactivity);
        edt_num1=(EditText)findViewById(R.id.edt_num1);
        edt_num2=(EditText)findViewById(R.id.edt_num2);
        edt_num3=(EditText)findViewById(R.id.edt_num3);
        edt_num4=(EditText)findViewById(R.id.edt_num4);
        edt_num5=(EditText)findViewById(R.id.edt_num5);
        edt_num6=(EditText)findViewById(R.id.edt_num6);
        btn_verify=(Button) findViewById(R.id.btn_verify);
        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code=edt_num1.getText().toString()+edt_num2.getText().toString()+edt_num3.getText().toString()+edt_num4.getText().toString()+edt_num5.getText().toString()+edt_num6.getText().toString();


                if (!Commonfunctions.checkVerificationcode(code)) {
                    Toast.makeText(Verifyactivity.this, "Enter valid verification code", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
            });
        }
    }






