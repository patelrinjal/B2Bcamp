package com.example.b2bcamp.adaptor;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;  
import android.view.ViewGroup;  
import android.widget.ImageView;  
import android.widget.RelativeLayout;  
import android.widget.TextView;  
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;


import com.example.b2bcamp.ProductDetailActivity;
import com.example.b2bcamp.R;
import com.example.b2bcamp.Utility.AllSharedPrefernces;
import com.example.b2bcamp.Utility.Constants;
import com.example.b2bcamp.models.ProductResultVo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
    private List<ProductResultVo> listdata;

    OnItemClickListener itemClickListener;

    AllSharedPrefernces allSharedPrefernces = null;

    Context mCOntext;
  
   // RecyclerView recyclerView;  
    public MyListAdapter(Context context ,List<ProductResultVo> listdata, OnItemClickListener listener) {
        this.listdata = listdata;
        this.itemClickListener = listener;
        this.mCOntext = context;

        allSharedPrefernces = new AllSharedPrefernces(mCOntext);
    }  
    @Override  
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {  
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());  
        View listItem= layoutInflater.inflate(R.layout.layout_product_row1, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);  
        return viewHolder;  
    }  
  
    @Override  
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final ProductResultVo dcotorListDataVo = listdata.get(position);
        holder.product_name.setText( dcotorListDataVo.getProductName());
        holder.product_price.setText(Constants.RUPEE_SIGN + dcotorListDataVo.getProductPrice());

        if (!TextUtils.isEmpty(dcotorListDataVo.getPImg1())) {
            Picasso.get().load(Constants.IMAGE_Url + dcotorListDataVo.getPImg1()) .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).networkPolicy(NetworkPolicy.NO_CACHE).placeholder(R.drawable.placeholder).resize(200,200).into(holder.img_product);
        }
        else {
            Picasso.get().load(R.drawable.placeholder).into(holder.img_product);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(holder.itemView.getContext(), ProductDetailActivity.class);
                i.putExtra("data",new Gson().toJson(dcotorListDataVo));
                holder.itemView.getContext().startActivity(i);

            }
        });

        if (CheckProductInCart(dcotorListDataVo)) {
            holder.txt_addtocart.setText("Go TO CART");
        }
        else {
            holder.txt_addtocart.setText("ADD TO CART");
        }

        holder.txt_addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.txt_addtocart.getText().toString().equalsIgnoreCase("go to cart")) {

                    if (itemClickListener != null) {
                        itemClickListener.onGotoCartitemclick(position, dcotorListDataVo);
                    }

                }
                else {
                    holder.txt_addtocart.setText("GO TO CART");
                    addProductToCart("1",dcotorListDataVo);
                }

            }
        });

    }  
  
  
    @Override  
    public int getItemCount() {  
        return listdata.size();
    }  
  
    public static class ViewHolder extends RecyclerView.ViewHolder {  

        public ImageView img_product;
        public TextView product_name,product_price,txt_addtocart;

        public ViewHolder(View itemView) {  
            super(itemView);
            this.img_product = (ImageView) itemView.findViewById(R.id.img_product);
            this.product_name = (TextView) itemView.findViewById(R.id.product_name);
            this.product_price = (TextView) itemView.findViewById(R.id.product_price);
            this.txt_addtocart = (TextView) itemView.findViewById(R.id.txt_addtocart);
        }
    }


    public void sortList(int mode) {
        //0 = newest
        //1 = low-to-high
        //2 = high-to-low
        if (mode == 1) {

            Collections.sort(listdata, new Comparator< ProductResultVo >() {
                @Override public int compare(ProductResultVo p1, ProductResultVo p2) {
                    return Integer.parseInt(p1.getProductPrice())-Integer.parseInt(p2.getProductPrice()); // Ascending
                }
            });

        }
        else if (mode == 2) {

            Collections.sort(listdata, new Comparator< ProductResultVo >() {
                @Override public int compare(ProductResultVo p1, ProductResultVo p2) {
                    return Integer.parseInt(p2.getProductPrice())-Integer.parseInt(p1.getProductPrice()); // Ascending
                }
            });

        }
        else if (mode == 0) {

            Collections.sort(listdata, new Comparator< ProductResultVo >() {
                @Override public int compare(ProductResultVo p1, ProductResultVo p2) {
                    return Integer.parseInt(p2.getProductId())-Integer.parseInt(p1.getProductId()); // Ascending
                }
            });

        }

        notifyDataSetChanged();


    }


    public interface OnItemClickListener {
        public void onitemclick(int pos,ProductResultVo dataVo);
        public void onGotoCartitemclick(int pos,ProductResultVo dataVo);
    }

    public boolean CheckProductInCart(ProductResultVo productListVo) {

        try {

            boolean isExist = false;

            Type listType = new TypeToken<List<ProductResultVo>>(){}.getType();
            List<ProductResultVo> productListVos = new Gson().fromJson(allSharedPrefernces.getCartList(), listType);

            if(productListVos != null) {
                if(productListVos.size() > 0) {
                    for (int i =0;i<productListVos.size();i++) {
                        if(productListVo.getProductId().equalsIgnoreCase(productListVos.get(i).getProductId())) {

                            isExist = true;
                            break;
                        }
                    }
                }
            }
            return isExist;
        }
        catch (Exception e) {
            e.printStackTrace();

            return false;
        }

    }

    public void addProductToCart(String qty , ProductResultVo productListVo) {

        try {

            Type listType = new TypeToken<List<ProductResultVo>>(){}.getType();
            List<ProductResultVo> productListVos = new Gson().fromJson(allSharedPrefernces.getCartList(), listType);

            if(productListVos == null)
                productListVos = new ArrayList<>();


            productListVo.setQty(qty);
            productListVos.add(productListVo);

            allSharedPrefernces.setCartList(new Gson().toJson(productListVos));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }


}  
