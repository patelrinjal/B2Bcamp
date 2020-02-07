package com.example.b2bcamp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.b2bcamp.Utility.Commonfunctions;
import com.example.b2bcamp.Utility.Constants;
import com.example.b2bcamp.Utility.DataInterface;
import com.example.b2bcamp.Utility.Webservice_Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Addproductactivity extends AppCompatActivity implements DataInterface {

    Spinner spn_1;
    EditText edt_name,edt_price,edt_description;
    ImageView img1,img2,img3;
    Button btn_submit;
    Webservice_Volley volley = null;

    ArrayList<String> list=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproductactivity);
        spn_1 = (Spinner) findViewById(R.id.spn_1);
        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_price = (EditText) findViewById(R.id.edt_price);
        edt_description = (EditText) findViewById(R.id.edt_description);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);


        list.add("Electronics");
        list.add("Footwear");
        list.add("Jewellery");
        list.add("Cloths");
        list.add("Cosmetics");
        list.add("Watches");

        ArrayAdapter<String> da=new ArrayAdapter<>(Addproductactivity.this,android.R.layout.simple_spinner_dropdown_item,list);
        spn_1.setAdapter(da);




        volley = new Webservice_Volley(this, this);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!Commonfunctions.checkstring(edt_name.getText().toString())) {
                    edt_name.setError("Please enter your name");
                    return;
                }

                if (!Commonfunctions.checkstring(edt_price.getText().toString())) {
                    edt_price.setError("Please enter your price");
                    return;
                }
                if (!Commonfunctions.checkstring(edt_description.getText().toString())) {
                    edt_description.setError("Please enter product decription");
                    return;
                }

                String url = Constants.Webserive_Url + "product.php";

                HashMap<String, String> params = new HashMap<>();

                params.put("category_id", String.valueOf(spn_1.getSelectedItemPosition()+1));
                params.put("product_name", edt_name.getText().toString());
                params.put("p_img1","");
                params.put("p_img2","");
                params.put("p_img3","");
                params.put("product_price", edt_price.getText().toString());
                params.put("product_description", edt_description.getText().toString());
                volley.CallVolley(url, params, "product");
            }
        });
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {
        Toast.makeText(this, jsonObject.toString(), Toast.LENGTH_SHORT).show();


    }

    public void ClickonLogin(View view) {

        finish();

    }
}














