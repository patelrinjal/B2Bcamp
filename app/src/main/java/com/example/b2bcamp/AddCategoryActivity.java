package com.example.b2bcamp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.b2bcamp.Utility.Commonfunctions;
import com.example.b2bcamp.Utility.Constants;
import com.example.b2bcamp.Utility.DataInterface;
import com.example.b2bcamp.Utility.Webservice_Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class AddCategoryActivity extends AppCompatActivity implements DataInterface {

    EditText category_name;
    ImageView img1;
    Button btn_submit;
    Webservice_Volley volley = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        category_name = (EditText) findViewById(R.id.category_name);
        img1 = (ImageView) findViewById(R.id.img1);

        volley = new Webservice_Volley(this, this);


        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!Commonfunctions.checkstring(category_name.getText().toString())) {
                    category_name.setError("Please enter your name");
                    return;
                }
                String url = Constants.Webserive_Url + "category.php";



                HashMap<String, String> params = new HashMap<>();
                params.put("category_name", category_name.getText().toString());
                params.put("seller_id","1");
                volley.CallVolley(url, params, "category");

            }
        });
    }
        @Override
        public void getData(JSONObject jsonObject, String tag) {

            try {

                Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                if (jsonObject.getString("response").equalsIgnoreCase("1")) {

                    finish();

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
