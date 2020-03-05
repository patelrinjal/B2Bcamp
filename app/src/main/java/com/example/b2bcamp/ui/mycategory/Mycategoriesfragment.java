package com.example.b2bcamp.ui.mycategory;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.b2bcamp.AddCategoryActivity;
import com.example.b2bcamp.Addproductactivity;
import com.example.b2bcamp.R;
import com.example.b2bcamp.Utility.AllSharedPrefernces;
import com.example.b2bcamp.Utility.Constants;
import com.example.b2bcamp.Utility.DataInterface;
import com.example.b2bcamp.Utility.Webservice_Volley;
import com.example.b2bcamp.adaptor.CategorylistAdapter;
import com.example.b2bcamp.adaptor.SupplierProductListAdaptor;
import com.example.b2bcamp.models.CategoryinfoVo;
import com.example.b2bcamp.models.ProductInfoVo;
import com.example.b2bcamp.ui.tools.ToolsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class Mycategoriesfragment extends Fragment implements DataInterface {

    private ToolsViewModel toolsViewModel;

    FloatingActionButton fabAdd;

    RecyclerView recycler_mycategorylist_list;
    Webservice_Volley volley=null;

    AllSharedPrefernces allSharedPrefernces = null;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_mycategoriesfragment, container, false);

        recycler_mycategorylist_list=(RecyclerView) root.findViewById(R.id.recycler_mycategorylist_list);
        recycler_mycategorylist_list.setLayoutManager(new GridLayoutManager(getActivity(),2));

        fabAdd  = (FloatingActionButton)root.findViewById(R.id.fabAdd);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showAddCategoryDialog();

            }
        });
        volley = new Webservice_Volley(getActivity(),this);

        allSharedPrefernces = new AllSharedPrefernces(getActivity());

        String url = Constants.Webserive_Url + "getcategorybyseller.php";

        HashMap<String,String> params = new HashMap<>();
        params.put("seller_id",allSharedPrefernces.getCustomerNo());

        volley.CallVolley(url,params,"getcategorybyseller");

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        loadData();

    }

    public void loadData() {

        String url = Constants.Webserive_Url + "getcategorybyseller.php";

        HashMap<String,String> params = new HashMap<>();
        params.put("seller_id",allSharedPrefernces.getCustomerNo());

        volley.CallVolley(url,params,"getcategorybyseller");

    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {
        try {

            if (tag.equalsIgnoreCase("category")) {

                Toast.makeText(getActivity(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                if (jsonObject.getString("response").equalsIgnoreCase("1")) {

                    loadData();

                }

            }
            else {

                CategoryinfoVo categoryinfoVo = new Gson().fromJson(jsonObject.toString(), CategoryinfoVo.class);
                if (categoryinfoVo != null) {
                    if (categoryinfoVo.getCategoryResultVo() != null) {
                        if (categoryinfoVo.getCategoryResultVo().size() > 0) {
                            CategorylistAdapter adaptor = new CategorylistAdapter(getActivity(), categoryinfoVo.getCategoryResultVo());
                            recycler_mycategorylist_list.setAdapter(adaptor);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void showAddCategoryDialog() {

        try {

            final Dialog d = new Dialog(getActivity());
            d.setContentView(R.layout.activity_add_category);

            int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
            int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(d.getWindow().getAttributes());
            lp.width = width;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            d.show();
            d.getWindow().setAttributes(lp);

            final EditText category_name = (EditText)d.findViewById(R.id.category_name);
            Button btn_submit = (Button) d.findViewById(R.id.btn_submit);

            btn_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (TextUtils.isEmpty(category_name.getText().toString())) {
                        category_name.setError("Enter Category Name");
                        return;
                    }

                    String url = Constants.Webserive_Url + "category.php";



                    HashMap<String, String> params = new HashMap<>();
                    params.put("category_name", category_name.getText().toString());
                    params.put("seller_id",allSharedPrefernces.getCustomerNo());
                    volley.CallVolley(url, params, "category");

                    d.dismiss();

                }
            });

            d.show();



        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }


}
