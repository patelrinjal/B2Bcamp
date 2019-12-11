package com.example.b2bcamp;

import androidx.appcompat.app.AppCompatActivity;

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

public class Usersignup extends AppCompatActivity implements DataInterface {
    EditText edt_name,edt_email,edt_contact,edt_city,edt_state,edt_password,edt_repassword;
    Button btn_signup;
    TextView txt_login;
    Webservice_Volley volley=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersignup);
        edt_name=(EditText)findViewById(R.id.edt_name);
        edt_email=(EditText)findViewById(R.id.edt_email);
        edt_contact=(EditText)findViewById(R.id.edt_contact);
        edt_city=(EditText)findViewById(R.id.edt_city);
        edt_state=(EditText)findViewById(R.id.edt_state);
        edt_password=(EditText)findViewById(R.id.edt_password);
        edt_repassword=(EditText)findViewById(R.id.edt_repassword);
        txt_login=(TextView)findViewById(R.id.txt_login);

        volley = new Webservice_Volley(this,this);
        btn_signup=(Button)findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Commonfunctions.checkstring(edt_name.getText().toString()))
                {
                    edt_name.setError("Please enter your name");
                    return;
                }
                if (!Commonfunctions.checkEmail(edt_email.getText().toString()))
                {
                    edt_email.setError("Please enter valid email");
                    return;
                }
                if (!Commonfunctions.checkContactNo(edt_contact.getText().toString()))
                {
                    edt_contact.setError("Please enter 10 digit contact no");
                    return;
                }
                if (!Commonfunctions.checkstring(edt_city.getText().toString()))
                {
                    edt_city.setError("Please enter valid city name");
                    return;
                }
                if (!Commonfunctions.checkstring(edt_state.getText().toString()))
                {
                    edt_state.setError("Please enter valid state name");
                    return;
                }
                if (!Commonfunctions.checkPassword(edt_password.getText().toString()))
                {
                    edt_password.setError("Please enter valid password");
                    return;
                }

                if (!Commonfunctions.checkPassword(edt_repassword.getText().toString()))
                {
                    edt_repassword.setError("Reenter valid password");
                    return;
                }

                String url = Constants.Webserive_Url + "registration.php";

                HashMap<String,String> params = new HashMap<>();
                params.put("user_name",edt_name.getText().toString());
                params.put("email",edt_email.getText().toString());
                params.put("user_type","");
                params.put("password",edt_password.getText().toString());
                params.put("profile_pic","");
                params.put("contact_num",edt_contact.getText().toString());
                params.put("city",edt_city.getText().toString());
                params.put("state_name",edt_state.getText().toString());

                volley.CallVolley(url,params,"registration");
            }
        });
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {
        Toast.makeText(this,jsonObject.toString(),Toast.LENGTH_SHORT).show();


    }
}
