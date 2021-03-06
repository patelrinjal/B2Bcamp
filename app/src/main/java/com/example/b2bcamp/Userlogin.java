package com.example.b2bcamp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b2bcamp.Utility.AllSharedPrefernces;
import com.example.b2bcamp.Utility.Commonfunctions;
import com.example.b2bcamp.Utility.Constants;
import com.example.b2bcamp.Utility.DataInterface;
import com.example.b2bcamp.Utility.Webservice_Volley;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.json.JSONObject;

import java.util.HashMap;

public class Userlogin extends AppCompatActivity implements DataInterface {
    EditText edt_contact, edt_password;
    Button btn_login;
    TextView txt_forgot_password, txt_signup;
    Webservice_Volley volley = null;

    AllSharedPrefernces allSharedPrefernces = null;
    private String mToken = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);
        edt_contact = (EditText) findViewById(R.id.edt_contact);
        edt_password = (EditText) findViewById(R.id.edt_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        txt_forgot_password = (TextView) findViewById(R.id.txt_forgot_password);
        txt_signup = (TextView) findViewById(R.id.txt_signup);

        allSharedPrefernces = new AllSharedPrefernces(this);
        volley = new Webservice_Volley(this, this);

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( Userlogin.this,  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                mToken = instanceIdResult.getToken();
                Log.e("MY_TOKEN","==>" + mToken);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Commonfunctions.checkContactNo(edt_contact.getText().toString())) {
                    edt_contact.setError("Please enter 10 digit contact no");
                    return;
                }
                if (!Commonfunctions.checkPassword(edt_password.getText().toString())) {
                    edt_password.setError("Password shoud be 6 char. long");
                    return;
                }

                String url = Constants.Webserive_Url + "login.php";

                HashMap<String, String> params = new HashMap<>();
                params.put("contact_num", edt_contact.getText().toString());
                params.put("password", edt_password.getText().toString());
                params.put("device_token",mToken);

                volley.CallVolley(url, params, "login");

            }
        });
        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Userlogin.this, Usersignup.class);
                startActivity(i);
            }
        });
        txt_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Userlogin.this, ForgotpasswordActivity.class);
                i.putExtra("type", "user");
                startActivity(i);
            }
        });
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {
        try {

            Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();

            if (jsonObject.getString("response").equalsIgnoreCase("1")) {

                allSharedPrefernces.setUserLogin(true);
                allSharedPrefernces.setUserType("user");

                allSharedPrefernces.setCustomerNo(jsonObject.getString("id"));
                allSharedPrefernces.setCustomerData(jsonObject.getJSONObject("data").toString());

                JSONObject jobj = jsonObject.getJSONObject("data");

                if (jobj != null) {
                    allSharedPrefernces.setCustomerName(jobj.getString("user_name"));
                }

                Intent i = new Intent(Userlogin.this, Sellerhomepage.class);
                startActivity(i);

                finishAffinity();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
