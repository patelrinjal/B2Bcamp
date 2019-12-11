package com.example.b2bcamp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.b2bcamp.Utility.Commonfunctions;
import com.example.b2bcamp.Utility.Constants;

import java.util.HashMap;

public class ForgotpasswordActivity extends AppCompatActivity {
    EditText edt_contact;
    Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        edt_contact = (EditText) findViewById(R.id.edt_contact);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Commonfunctions.checkContactNo(edt_contact.getText().toString())) {
                    edt_contact.setError("Please enter 10 digit contact no");
                    return;
                }
                String url = Constants.Webserive_Url + "registration.php";

                HashMap<String, String> params = new HashMap<>();
                params.put("contact_num", edt_contact.getText().toString());
            }
        });
    }
}
