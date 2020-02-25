package com.example.b2bcamp.ui.myinquiry;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.b2bcamp.Addproductactivity;
import com.example.b2bcamp.R;
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
public class MyInquiry extends Fragment implements DataInterface {

    private ToolsViewModel toolsViewModel;

    FloatingActionButton fabAdd;

    RecyclerView recycler_myinquiry_list_list;
    Webservice_Volley volley=null;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_my_inquiry, container, false);

        recycler_myinquiry_list_list=(RecyclerView) root.findViewById(R.id.recycler_my_inquiry_list);
        recycler_myinquiry_list_list.setLayoutManager(new GridLayoutManager(getActivity(),2));


        volley = new Webservice_Volley(getActivity(),this);

        String url = Constants.Webserive_Url + "getinquiryrybyseller.php";

        HashMap<String,String> params = new HashMap<>();
        params.put("seller_id","1");

        volley.CallVolley(url,params,"getinquirybyseller");

        return root;
    }
    @Override
    public void getData(JSONObject jsonObject, String tag) {

    }


}
