package com.example.b2bcamp;

import androidx.appcompat.app.AppCompatActivity;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifyactivity);
        Pin_View=(PinView) findViewById(R.id.Pin_View);
        btn_verify=(Button) findViewById(R.id.btn_verify);
        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Commonfunctions.checkstring(Pin_View.getText().toString()))
                {
                    Pin_View.setError("Please enter your name");
                    return;
                }
                String url = Constants.Webserive_Url + "registration.php";

                HashMap<String,String> params = new HashMap<>();
                params.put("pin number",Pin_View.getText().toString());




            }
            });
        }

    }







