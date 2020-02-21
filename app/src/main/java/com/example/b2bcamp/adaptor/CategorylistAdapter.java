package com.example.b2bcamp.adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.b2bcamp.Categoryproductlistactivity;
import com.example.b2bcamp.R;
import com.example.b2bcamp.models.CategoryResultVo;
import com.example.b2bcamp.models.ProductResultVo;

import java.util.List;


public class CategorylistAdapter extends RecyclerView.Adapter<CategorylistAdapter.ViewHolder>{
    private List<CategoryResultVo> listdata;
    Context mCOntext;

   // RecyclerView recyclerView;
    public CategorylistAdapter(Context context,List<CategoryResultVo> listdata) {
        this.listdata = listdata;
        this.mCOntext = context;

    }  
    @Override  
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {  
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());  
        View listItem= layoutInflater.inflate(R.layout.layout_category_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);  
        return viewHolder;  
    }  
  
    @Override  
    public void onBindViewHolder(ViewHolder holder, int position) {

        final CategoryResultVo dcotorListDataVo = listdata.get(position);
        holder.category_name.setText( dcotorListDataVo.getCategoryName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mCOntext, Categoryproductlistactivity.class);
                i.putExtra("category_id",dcotorListDataVo.getCategoryId());
                mCOntext.startActivity(i);

            }
        });

    }  
  
  
    @Override  
    public int getItemCount() {  
        return listdata.size();
    }  
  
    public static class ViewHolder extends RecyclerView.ViewHolder {  

        public TextView category_name;

        public ViewHolder(View itemView) {  
            super(itemView);

            this.category_name = (TextView) itemView.findViewById(R.id.txt_categoryname);

        }
    }  
}  
