package com.example.b2bcamp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.b2bcamp.R;
import com.example.b2bcamp.SearchActivity;
import com.example.b2bcamp.Utility.Constants;
import com.example.b2bcamp.Utility.DataInterface;
import com.example.b2bcamp.Utility.Webservice_Volley;
import com.example.b2bcamp.adaptor.CategorylistAdapter;
import com.example.b2bcamp.adaptor.SimpleFragmentPagerAdapter;
import com.example.b2bcamp.adaptor.SupplierProductListAdaptor;
import com.example.b2bcamp.models.CategoryResultVo;
import com.example.b2bcamp.models.CategoryinfoVo;
import com.example.b2bcamp.models.ProductInfoVo;
import com.example.b2bcamp.ui.productcategory.CategorywiseProductFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment implements DataInterface {

    private HomeViewModel homeViewModel;
    RecyclerView recycler_category_list;
    EditText edt_search;
    ViewPager viewpager;
    TabLayout tab_layout;


    Webservice_Volley volley=null;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        recycler_category_list=(RecyclerView) root.findViewById(R.id.recycler_category_list);

        tab_layout=(TabLayout) root.findViewById(R.id.tab_layout);
        viewpager=(ViewPager) root.findViewById(R.id.viewpager);

        recycler_category_list.setLayoutManager(new GridLayoutManager(getActivity(),2));
        edt_search = (EditText) root.findViewById(R.id.edt_search);

        volley = new Webservice_Volley(getActivity(),this);

        String url = Constants.Webserive_Url + "getcategorylist.php";

        HashMap<String,String> params = new HashMap<>();
        volley.CallVolley(url,params, "getcategorylist");

        edt_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    String s = v.getText().toString();

                    Intent i = new Intent(getActivity(), SearchActivity.class);
                    i.putExtra("search_text",s);
                    startActivity(i);


                    return true;
                }


                return false;
            }
        });

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
                        /*CategorylistAdapter adaptor = new CategorylistAdapter(getActivity(),categoryinfoVo.getCategoryResultVo());
                        recycler_category_list.setAdapter(adaptor);*/

                        setupViewPager(categoryinfoVo.getCategoryResultVo());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void setupViewPager(List<CategoryResultVo> categoryResultVos) {

        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getActivity(),getActivity().getSupportFragmentManager());

        for (int i =0;i<categoryResultVos.size();i++) {

            if (!TextUtils.isEmpty(categoryResultVos.get(i).getCategoryName())) {

                CategorywiseProductFragment categorywiseProductFragment = new CategorywiseProductFragment();

                Bundle b = new Bundle();
                b.putString("categoryid", categoryResultVos.get(i).getCategoryId());

                categorywiseProductFragment.setArguments(b);

                adapter.addFragment(categorywiseProductFragment, categoryResultVos.get(i).getCategoryName().toUpperCase());
            }

        }

        viewpager.setAdapter(adapter);

        tab_layout.setupWithViewPager(viewpager);


    }

}