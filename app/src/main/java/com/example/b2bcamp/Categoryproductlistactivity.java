package com.example.b2bcamp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

import com.example.b2bcamp.Utility.Constants;
import com.example.b2bcamp.Utility.DataInterface;
import com.example.b2bcamp.Utility.Webservice_Volley;
import com.example.b2bcamp.adaptor.MyListAdapter;
import com.example.b2bcamp.models.ProductInfoVo;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

public class Categoryproductlistactivity extends AppCompatActivity implements DataInterface {

    RecyclerView recycler_product_list;
    Webservice_Volley volley=null;

    String category_id = "2";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoryproductlistactivity);
        recycler_product_list = (RecyclerView) findViewById(R.id.recycler_product_list);

        recycler_product_list.setLayoutManager(new LinearLayoutManager(this));


        volley = new Webservice_Volley(this,this);


        String url = Constants.Webserive_Url + "getproductbycategory.php";

        HashMap<String, String> params = new HashMap<>();
        params.put("category_id", category_id );
        volley.CallVolley(url, params, "getproductbycategory");

    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try
        {
            ProductInfoVo productInfoVo = new Gson().fromJson(jsonObject.toString(),ProductInfoVo.class);
            if (productInfoVo!=null)
            {
                if(productInfoVo.getResult()!=null)
                {
                    if(productInfoVo.getResult().size()>0)
                    {
                        MyListAdapter adapter = new MyListAdapter(productInfoVo.getResult());
                        recycler_product_list.setAdapter(adapter);
                    }
                }
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
