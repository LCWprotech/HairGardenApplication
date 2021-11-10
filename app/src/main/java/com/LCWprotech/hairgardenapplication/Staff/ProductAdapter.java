package com.LCWprotech.hairgardenapplication.Staff;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.LCWprotech.hairgardenapplication.R;
import com.LCWprotech.hairgardenapplication.Admin.UpdateProductModel;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context mcontext;
    private List<UpdateProductModel>updateProductModelList;
    DatabaseReference databaseReference;

    public ProductAdapter(Context context , List<UpdateProductModel>updateProductModelList){

        this.updateProductModelList = updateProductModelList;
        this.mcontext = context;
    }


    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.menu_product,parent,false);
        return new ProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {

        final UpdateProductModel updateProductModel = updateProductModelList.get(position);
        Glide.with(mcontext).load(updateProductModel.getImageURL()).into(holder.imageView);
        holder.name.setText(updateProductModel.getName());
        updateProductModel.getRandomUID();
        updateProductModel.getAdminId();
        holder.Price.setText("Price: RM"+updateProductModel.getPrice());

    }

    @Override
    public int getItemCount() {
        return updateProductModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name,Price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.menu_image);
            name = itemView.findViewById(R.id.productname);
            Price = itemView.findViewById(R.id.productprice);
        }
    }

}