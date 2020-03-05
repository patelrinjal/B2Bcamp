package com.example.b2bcamp.ui.mycomplain;


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
import com.example.b2bcamp.Utility.AllSharedPrefernces;
import com.example.b2bcamp.Utility.Constants;
import com.example.b2bcamp.Utility.DataInterface;
import com.example.b2bcamp.Utility.Webservice_Volley;
import com.example.b2bcamp.adaptor.CategorylistAdapter;
import com.example.b2bcamp.adaptor.ComplainListAdaptor;
import com.example.b2bcamp.adaptor.InquiryListAdaptor;
import com.example.b2bcamp.adaptor.SupplierProductListAdaptor;
import com.example.b2bcamp.models.CategoryinfoVo;
import com.example.b2bcamp.models.ComplainResponseVo;
import com.example.b2bcamp.models.InquiryResponseVo;
import com.example.b2bcamp.models.ProductInfoVo;
import com.example.b2bcamp.ui.tools.ToolsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyComplain extends Fragment implements DataInterface {

    private ToolsViewModel toolsViewModel;

    FloatingActionButton fabAdd;

    RecyclerView recycler_MyComplain_list_list;
    Webservice_Volley volley=null;

    AllSharedPrefernces allSharedPrefernces = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_my_complain, container, false);

        recycler_MyComplain_list_list=(RecyclerView) root.findViewById(R.id.recycler_my_complain_list);
        recycler_MyComplain_list_list.setLayoutManager(new LinearLayoutManager(getActivity()));

        allSharedPrefernces = new AllSharedPrefernces(getActivity());

        volley = new Webservice_Volley(getActivity(),this);

        String url = Constants.Webserive_Url + "getcomplainbyseller.php";

        HashMap<String,String> params = new HashMap<>();

        if (allSharedPrefernces.getUserType().equalsIgnoreCase("seller")) {
            url = Constants.Webserive_Url + "getcomplainbyseller.php";
                params.put("seller_id", allSharedPrefernces.getCustomerNo());
        }
        else {
            url = Constants.Webserive_Url + "getcomplainbyuser.php";
            params.put("user_id", allSharedPrefernces.getCustomerNo());
        }

        volley.CallVolley(url,params,"getcomplainbyseller");

        return root;
    }
    @Override
    public void getData(JSONObject jsonObject, String tag) {


        try {

            ComplainResponseVo inquiryResponseVo = new Gson().fromJson(jsonObject.toString(),ComplainResponseVo.class);

            if (inquiryResponseVo != null) {

                if (inquiryResponseVo.getResult().size() > 0) {

                    ComplainListAdaptor inquiryListAdaptor = new ComplainListAdaptor(inquiryResponseVo.getResult());
                    recycler_MyComplain_list_list.setAdapter(inquiryListAdaptor);

                }

            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }


}
