package com.example.b2bcamp.adaptor;

import android.view.LayoutInflater;
import android.view.View;  
import android.view.ViewGroup;  
import android.widget.ImageView;  
import android.widget.RelativeLayout;  
import android.widget.TextView;  
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;


import com.example.b2bcamp.R;
import com.example.b2bcamp.models.ProductResultVo;

import java.util.List;


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
    private List<ProductResultVo> listdata;
  
   // RecyclerView recyclerView;  
    public MyListAdapter(List<ProductResultVo> listdata) {
        this.listdata = listdata;  
    }  
    @Override  
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {  
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());  
        View listItem= layoutInflater.inflate(R.layout.layout_productrow, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);  
        return viewHolder;  
    }  
  
    @Override  
    public void onBindViewHolder(ViewHolder holder, int position) {

        ProductResultVo dcotorListDataVo = listdata.get(position);
        holder.product_name.setText( dcotorListDataVo.getProductName());
        holder.product_price.setText(dcotorListDataVo.getProductPrice());


    }  
  
  
    @Override  
    public int getItemCount() {  
        return listdata.size();
    }  
  
    public static class ViewHolder extends RecyclerView.ViewHolder {  

        public ImageView img_product;
        public TextView product_name,product_price;

        public ViewHolder(View itemView) {  
            super(itemView);
            this.img_product = (ImageView) itemView.findViewById(R.id.img_product);
            this.product_name = (TextView) itemView.findViewById(R.id.product_name);
            this.product_price = (TextView) itemView.findViewById(R.id.product_price);
        }
    }  
}  
