package com.example.b2bcamp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.example.b2bcamp.models.ProductResultVo;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class AddInquiryActivity extends AppCompatActivity implements DataInterface {
    TextView user_name,product_name;
    EditText inquiry_details;
    Button btn_submit;
    Webservice_Volley volley=null;

    ProductResultVo productResultVo = null;

    AllSharedPrefernces allSharedPrefernces = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inquiry);

        allSharedPrefernces = new AllSharedPrefernces(this);

        getSupportActionBar().setTitle("Post Inquiry");


        user_name=(TextView)findViewById(R.id.user_name);
        product_name=(TextView)findViewById(R.id.product_name);
        inquiry_details=(EditText) findViewById(R.id.inquiry_details);
        btn_submit=(Button)findViewById(R.id.btn_submit);
        volley = new Webservice_Volley(this,this);
        btn_submit = (Button) findViewById(R.id.btn_submit);

        String data = getIntent().getStringExtra("data");

        if (!TextUtils.isEmpty(data)) {
            productResultVo = new Gson().fromJson(data, ProductResultVo.class);

            if (productResultVo != null) {

                product_name.setText(productResultVo.getProductName());
                user_name.setText(productResultVo.getUserName());

            }
        }

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!Commonfunctions.checkstring(inquiry_details.getText().toString())) {
                    inquiry_details.setError("Please enter valid inquiry details");
                    return;
                }
                String url = Constants.Webserive_Url + "inquiry.php";

                HashMap<String,String> params = new HashMap<>();
                params.put("user_id", productResultVo.getSellerId());
                params.put("seller_id",allSharedPrefernces.getCustomerNo() );
                params.put("product_id", productResultVo.getProductId());
                params.put("productname", productResultVo.getProductName());
                params.put("inquiry_details",inquiry_details.getText().toString());
                params.put("inquiry_date",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

                params.put("inquiry_response","");
                params.put("status","pending");

                volley.CallVolley(url, params, "product");
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
}