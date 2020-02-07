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

    String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        edt_contact = (EditText) findViewById(R.id.edt_contact);
        btn_submit = (Button) findViewById(R.id.btn_submit);

        type = getIntent().getStringExtra("type");

        volley = new Webservice_Volley(this,this);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Commonfunctions.checkContactNo(edt_contact.getText().toString())) {
                    edt_contact.setError("Please enter 10 digit contact no");
                    return;
                }

                if (type.equalsIgnoreCase("seller")) {

                    String url = Constants.Webserive_Url + "seller_forgotpassword.php";

                    HashMap<String, String> params = new HashMap<>();
                    params.put("seller_contactno", edt_contact.getText().toString());
                    volley.CallVolley(url, params, "seller_forgotpassword");

                }
                else {


                    String url = Constants.Webserive_Url + "user_forgotpassword.php";

                    HashMap<String, String> params = new HashMap<>();
                    params.put("contact_num", edt_contact.getText().toString());
                    volley.CallVolley(url, params, "user_forgotpassword");
                }
            }
        });
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {

            Toast.makeText(this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();

            if (jsonObject.getString("response").equals("1")) {

                String code = jsonObject.getString("otp");
                String id = jsonObject.getString("id");

                Toast.makeText(this, "otp:"+ code, Toast.LENGTH_LONG).show();


                Intent i = new Intent(ForgotpasswordActivity.this,Verifyactivity.class);
                i.putExtra("otp",code);
                i.putExtra("id",id);
                i.putExtra("type",type);
                startActivity(i);

            }


        }
        catch (Exception e){
            e.printStackTrace();
        }



    }
    public void ClickonLogin(View view) {

        finish();
    }
}
