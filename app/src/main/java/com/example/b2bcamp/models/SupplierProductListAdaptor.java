package com.example.b2bcamp.models;

import android.view.LayoutInflater;
import android.view.View;  
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.b2bcamp.R;

import java.util.List;


public class SupplierProductListAdaptor extends RecyclerView.Adapter<SupplierProductListAdaptor.ViewHolder>{
    private List<ProductResultVo> listdata;
  
   // RecyclerView recyclerView;  
    public SupplierProductListAdaptor(List<ProductResultVo> listdata) {
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

        ProductResultVo  productResultVo= listdata.get(position);
        holder.product_name.setText( productResultVo.getProductName());
        holder.product_price.setText(productResultVo.getProductPrice());


    }  
  
  
    @Override  
    public int getItemCount() {  
        return listdata.size();
    }  
  
    public static class ViewHolder extends RecyclerView.ViewHolder {  

        public TextView product_name,product_price ;

        public ViewHolder(View itemView) {  
            super(itemView);  
            this.product_name = (TextView) itemView.findViewById(R.id.nav_product);
            this.product_price = (TextView) itemView.findViewById(R.id.nav_myproduct);
        }
    }  
}  


