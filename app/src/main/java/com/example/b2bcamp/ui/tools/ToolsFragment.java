package com.example.b2bcamp.ui.tools;

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
import androidx.recyclerview.widget.RecyclerView;

import com.example.b2bcamp.R;
import com.example.b2bcamp.Utility.Constants;
import com.example.b2bcamp.Utility.DataInterface;
import com.example.b2bcamp.Utility.Webservice_Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class ToolsFragment extends Fragment implements DataInterface {

    private ToolsViewModel toolsViewModel;

    RecyclerView recycler_product_list;
    Webservice_Volley volley=null;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);

        recycler_product_list=(RecyclerView) root.findViewById(R.id.recycler_product_list);
        volley = new Webservice_Volley(getActivity(),this);
        String url = Constants.Webserive_Url + "login.php";

        HashMap<String,String> params = new HashMap<>();
        params.put("seller_id","1");

        return root;
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

    }
}