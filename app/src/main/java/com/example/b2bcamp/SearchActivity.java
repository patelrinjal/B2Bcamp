package com.example.b2bcamp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b2bcamp.Utility.Constants;
import com.example.b2bcamp.Utility.DataInterface;
import com.example.b2bcamp.Utility.Webservice_Volley;
import com.example.b2bcamp.adaptor.MyListAdapter;
import com.example.b2bcamp.models.ProductInfoVo;
import com.example.b2bcamp.models.ProductResultVo;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

public class SearchActivity extends AppCompatActivity implements DataInterface, MyListAdapter.OnItemClickListener {

    RecyclerView recycler_searchproduct_list;
    Webservice_Volley volley = null;

    EditText edt_search;

    String search_text = "0";

    int filterMOde = -1;

    MyListAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().setTitle("Search Ptroduct");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recycler_searchproduct_list = (RecyclerView) findViewById(R.id.recycler_searchproduct_list);

        recycler_searchproduct_list.setLayoutManager(new GridLayoutManager(this, 2));
        edt_search = (EditText) findViewById(R.id.edt_search);

        volley = new Webservice_Volley(this, this);

        edt_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    search_text = v.getText().toString();

                    String url = Constants.Webserive_Url + "search_product.php";

                    HashMap<String, String> params = new HashMap<>();
                    params.put("search_text", search_text);
                    volley.CallVolley(url, params, "search_product");


                    return true;
                }


                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {
            ProductInfoVo productInfoVo = new Gson().fromJson(jsonObject.toString(), ProductInfoVo.class);
            if (productInfoVo != null) {
                if (productInfoVo.getResult() != null) {
                    if (productInfoVo.getResult().size() > 0) {
                        adapter = new MyListAdapter(this,productInfoVo.getResult(),this);
                        recycler_searchproduct_list.setAdapter(adapter);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void showFilterDialog() {

        try {

            final CharSequence[] choice = {"Newest","Low to high","High to low"};

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Sort By");
            alert.setSingleChoiceItems(choice, filterMOde, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    filterMOde = which;
                }
            });
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if (adapter != null)
                        adapter.sortList(filterMOde);

                }
            });
            alert.show();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void ClickOnFilter(View view) {
        showFilterDialog();
    }

    @Override
    public void onitemclick(int pos, ProductResultVo dataVo) {
    }

    @Override
    public void onGotoCartitemclick(int pos, ProductResultVo dataVo) {
        Intent i = new Intent(SearchActivity.this, CartActivity.class);
        startActivity(i);
    }
}
