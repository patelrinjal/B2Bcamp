package com.example.b2bcamp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.b2bcamp.Utility.Constants;
import com.example.b2bcamp.Utility.DataInterface;
import com.example.b2bcamp.Utility.Webservice_Volley;
import com.example.b2bcamp.adaptor.MyListAdapter;
import com.example.b2bcamp.models.ProductInfoVo;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

public class SearchActivity extends AppCompatActivity implements DataInterface {

    RecyclerView recycler_searchproduct_list;
    Webservice_Volley volley=null;

    String search_text = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recycler_searchproduct_list = (RecyclerView) findViewById(R.id.recycler_searchproduct_list);

        recycler_searchproduct_list.setLayoutManager(new GridLayoutManager(this,2));

        search_text = getIntent().getStringExtra("search_text");

        volley = new Webservice_Volley(this,this);


        String url = Constants.Webserive_Url + "search_product.php";

        HashMap<String, String> params = new HashMap<>();
        params.put("search_text", search_text );
        volley.CallVolley(url, params, "search_product");
    }
    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {
            ProductInfoVo productInfoVo = new Gson().fromJson(jsonObject.toString(), ProductInfoVo.class);
            if (productInfoVo != null) {
                if (productInfoVo.getResult() != null) {
                    if (productInfoVo.getResult().size() > 0) {
                        MyListAdapter adapter = new MyListAdapter(productInfoVo.getResult());
                        recycler_searchproduct_list.setAdapter(adapter);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    }
