package com.example.b2bcamp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b2bcamp.Utility.Commonfunctions;
import com.example.b2bcamp.Utility.Constants;
import com.example.b2bcamp.Utility.DataInterface;
import com.example.b2bcamp.Utility.Webservice_Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class Userlogin extends AppCompatActivity implements DataInterface {
    EditText edt_contact,edt_password;
    Button btn_login;
    TextView txt_forgot_password,txt_signup;
    Webservice_Volley volley=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);
        edt_contact=(EditText)findViewById(R.id.edt_contact);
        edt_password=(EditText)findViewById(R.id.edt_password);
        btn_login=(Button)findViewById(R.id.btn_login);
        txt_forgot_password=(TextView)findViewById(R.id.txt_forgot_password);
        txt_signup=(TextView)findViewById(R.id.txt_signup);

        volley = new Webservice_Volley(this,this);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Commonfunctions.checkContactNo(edt_contact.getText().toString()))
                {
                    edt_contact.setError("Please enter 10 digit contact no");
                    return;
                }
                if (!Commonfunctions.checkPassword(edt_password.getText().toString()))
                {
                    edt_password.setError("Password shoud be 6 char. long");
                    return;
                }

                String url = Constants.Webserive_Url + "login.php";

                HashMap<String,String> params = new HashMap<>();
                params.put("contact_num",edt_contact.getText().toString());
                params.put("password",edt_password.getText().toString());

                volley.CallVolley(url,params,"login");

            }
        });
        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Userlogin.this,Usersignup.class);
                startActivity(i);
            }
        });
        txt_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Userlogin.this,ForgotpasswordActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {
        Toast.makeText(this,jsonObject.toString(),Toast.LENGTH_SHORT).show();

        Intent i=new Intent(Userlogin.this,Customerhomepage.class);
        startActivity(i);
    }
}
