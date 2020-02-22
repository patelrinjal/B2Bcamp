package com.example.b2bcamp.ui.mycategory;


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
public class Mycategoriesfragment extends Fragment implements DataInterface {

    private ToolsViewModel toolsViewModel;

    FloatingActionButton fabAdd;

    RecyclerView recycler_mycategorylist_list;
    Webservice_Volley volley=null;




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

                Intent i = new Intent(getActivity(), Addproductactivity.class);
                startActivity(i);

            }
        });
        volley = new Webservice_Volley(getActivity(),this);

        String url = Constants.Webserive_Url + "getcategorybyseller.php";

        HashMap<String,String> params = new HashMap<>();
        params.put("seller_id","1");

        volley.CallVolley(url,params,"getcategorybyseller");

        return root;
    }
    @Override
    public void getData(JSONObject jsonObject, String tag) {
        try {
            CategoryinfoVo categoryinfoVo=new Gson().fromJson(jsonObject.toString(),CategoryinfoVo.class);
            if(categoryinfoVo!=null)
            {
                if(categoryinfoVo.getCategoryResultVo()!=null)
                {
                    if(categoryinfoVo.getCategoryResultVo().size()>0){
                        CategorylistAdapter adaptor = new CategorylistAdapter(getActivity(),categoryinfoVo.getCategoryResultVo());
                        recycler_mycategorylist_list.setAdapter(adaptor);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
