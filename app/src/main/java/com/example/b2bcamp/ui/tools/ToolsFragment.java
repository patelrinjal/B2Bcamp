package com.example.b2bcamp.ui.tools;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b2bcamp.Addproductactivity;
import com.example.b2bcamp.R;
import com.example.b2bcamp.Utility.Constants;
import com.example.b2bcamp.Utility.DataInterface;
import com.example.b2bcamp.Utility.Webservice_Volley;
import com.example.b2bcamp.adaptor.SupplierProductListAdaptor;
import com.example.b2bcamp.models.ProductInfoVo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

public class ToolsFragment extends Fragment implements DataInterface {

    private ToolsViewModel toolsViewModel;

    FloatingActionButton fabAdd;

    RecyclerView recycler_product_list;
    Webservice_Volley volley=null;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_tools, container, false);

        recycler_product_list=(RecyclerView) root.findViewById(R.id.recycler_product_list);
        recycler_product_list.setLayoutManager(new LinearLayoutManager(getActivity()));

        fabAdd  = (FloatingActionButton)root.findViewById(R.id.fabAdd);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), Addproductactivity.class);
                startActivity(i);

            }
        });

        volley = new Webservice_Volley(getActivity(),this);

        String url = Constants.Webserive_Url + "getproductbyseller.php";

        HashMap<String,String> params = new HashMap<>();
        params.put("seller_id","1");

        volley.CallVolley(url,params,"getproductbyseller");

        return root;
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {
        try {
            ProductInfoVo productInfoVo=new Gson().fromJson(jsonObject.toString(),ProductInfoVo.class);
            if(productInfoVo!=null)
            {
                if(productInfoVo.getResult()!=null)
                {
                    if(productInfoVo.getResult().size()>0){
                        SupplierProductListAdaptor adaptor = new SupplierProductListAdaptor(productInfoVo.getResult());
                        recycler_product_list.setAdapter(adaptor);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}