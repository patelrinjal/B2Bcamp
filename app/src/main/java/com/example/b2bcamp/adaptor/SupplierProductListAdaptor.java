package com.example.b2bcamp.adaptor;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;  
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.b2bcamp.R;
import com.example.b2bcamp.Utility.Constants;
import com.example.b2bcamp.models.ProductResultVo;
import com.squareup.picasso.Picasso;

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
        holder.product_price.setText(Constants.RUPEE_SIGN + productResultVo.getProductPrice());

        if (!TextUtils.isEmpty(productResultVo.getPImg1())) {
            Picasso.get().load(Constants.IMAGE_Url + productResultVo.getPImg1()).resize(200,200).into(holder.img_product);
        }


    }  
  
  
    @Override  
    public int getItemCount() {  
        return listdata.size();
    }  
  
    public static class ViewHolder extends RecyclerView.ViewHolder {  

        public TextView product_name,product_price ;
        ImageView img_product;

        public ViewHolder(View itemView) {  
            super(itemView);  
            this.product_name = (TextView) itemView.findViewById(R.id.product_name);
            this.product_price = (TextView) itemView.findViewById(R.id.product_price);
            this.img_product = (ImageView) itemView.findViewById(R.id.img_product);
        }
    }  
}  


