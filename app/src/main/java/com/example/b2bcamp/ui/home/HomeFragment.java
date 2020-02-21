package com.example.b2bcamp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b2bcamp.R;
import com.example.b2bcamp.Utility.Constants;
import com.example.b2bcamp.Utility.DataInterface;
import com.example.b2bcamp.Utility.Webservice_Volley;
import com.example.b2bcamp.adaptor.CategorylistAdapter;
import com.example.b2bcamp.adaptor.SupplierProductListAdaptor;
import com.example.b2bcamp.models.CategoryinfoVo;
import com.example.b2bcamp.models.ProductInfoVo;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

public class HomeFragment extends Fragment implements DataInterface {

    private HomeViewModel homeViewModel;
    RecyclerView recycler_category_list;
    Webservice_Volley volley=null;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recycler_category_list=(RecyclerView) root.findViewById(R.id.recycler_category_list);

        recycler_category_list.setLayoutManager(new GridLayoutManager(getActivity(),2));

        volley = new Webservice_Volley(getActivity(),this);

        String url = Constants.Webserive_Url + "getcategorylist.php";

        HashMap<String,String> params = new HashMap<>();
        volley.CallVolley(url,params, "getcategorylist");

        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
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
                        CategorylistAdapter adaptor = new CategorylistAdapter(categoryinfoVo.getCategoryResultVo());
                        recycler_category_list.setAdapter(adaptor);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}