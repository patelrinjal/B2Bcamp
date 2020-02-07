package com.example.b2bcamp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.b2bcamp.Utility.Commonfunctions;
import com.example.b2bcamp.Utility.Constants;
import com.example.b2bcamp.Utility.DataInterface;
import com.example.b2bcamp.Utility.Webservice_Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class ForgotpasswordActivity extends AppCompatActivity implements DataInterface {
    EditText edt_contact;
    Button btn_submit;
    Webservice_Volley volley=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        edt_contact = (EditText) findViewById(R.id.edt_contact);
        btn_submit = (Button) findViewById(R.id.btn_submit);

        volley = new Webservice_Volley(this,this);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Commonfunctions.checkContactNo(edt_contact.getText().toString())) {
                    edt_contact.setError("Please enter 10 digit contact no");
                    return;
                }
                String url = Constants.Webserive_Url + "user_forgotpassword.php";

                HashMap<String, String> params = new HashMap<>();
                params.put("contact_num", edt_contact.getText().toString());
                volley.CallVolley(url,params,"user_forgotpassword");
            }
        });
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {
        Toast.makeText(this,jsonObject.toString(),Toast.LENGTH_SHORT).show();

        Intent i=new Intent(ForgotpasswordActivity.this,Resetactivity.class);
        startActivity(i);
    }
    public void ClickonLogin(View view) {

        finish();
    }
}
