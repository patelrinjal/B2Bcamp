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

public class Resetactivity extends AppCompatActivity implements DataInterface {
    EditText edt_password,edt_repassword;
    Button btn_reset;
    Webservice_Volley volley=null;
    String user_id="8";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetactivity);
        edt_password=(EditText)findViewById(R.id.edt_password);
        edt_repassword=(EditText)findViewById(R.id.edt_repassword);
        btn_reset = (Button) findViewById(R.id.btn_reset);
        volley = new Webservice_Volley(this,this);
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
                if (!edt_password.getText().toString().equals(edt_repassword.getText().toString())) {
                    edt_repassword.setError("Both password not match");
                    return;
                }

                String url = Constants.Webserive_Url + "user_resetpassword.php";

                HashMap<String, String> params = new HashMap<>();
                params.put("user_id",user_id);
                params.put("password", edt_password.getText().toString());
                volley.CallVolley(url,params,"user_resetpassword");



            }

            });
        }

    @Override
    public void getData(JSONObject jsonObject, String tag) {
        Toast.makeText(this,jsonObject.toString(),Toast.LENGTH_SHORT).show();
    }
    public void ClickonLogin(View view) {

        finish();

    }
}




