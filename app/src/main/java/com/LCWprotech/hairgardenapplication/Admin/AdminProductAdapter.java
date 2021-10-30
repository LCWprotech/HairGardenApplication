package com.LCWprotech.hairgardenapplication.Admin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.LCWprotech.hairgardenapplication.R;
import com.LCWprotech.hairgardenapplication.Admin.UpdateProductModel;
import java.util.List;


public class AdminProductAdapter extends RecyclerView.Adapter<AdminProductAdapter.ViewHolder> {

    private Context mcont;
    private List<UpdateProductModel> updateProductModelList;

    public AdminProductAdapter(Context context , List<UpdateProductModel>updateProductModelList){
        this.updateProductModelList = updateProductModelList;
        this.mcont = context;
    }

    @NonNull
    @Override
    public AdminProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcont).inflate(R.layout.admin_menu_update_delete,parent,false);
        return new AdminProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminProductAdapter.ViewHolder holder, int position) {

        final UpdateProductModel updateProductModel = updateProductModelList.get(position);
        holder.name.setText(updateProductModel.getName());
        updateProductModel.getRandomUID();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcont,UpdateDeleteProduct.class);
                intent.putExtra("updatedeleteproduct",updateProductModel.getRandomUID());
                mcont.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return updateProductModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
        }
    }
}