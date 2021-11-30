package com.LCWprotech.hairgardenapplication.Admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.LCWprotech.hairgardenapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminProductFragment extends Fragment {

    RecyclerView recyclerView;
    private List<UpdateProductModel> updateProductModelList;
    private AdminProductAdapter adapter;
    DatabaseReference data;
    private String State,City,Area;
    Button BtnProduct;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_admin_product,null);
        getActivity().setTitle("Admin Product");
        setHasOptionsMenu(true);
        BtnProduct = (Button) v.findViewById(R.id.btnAddProduct);
        recyclerView = v.findViewById(R.id.adminProductRecycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        updateProductModelList = new ArrayList<>();
        String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        data = FirebaseDatabase.getInstance().getReference("Admin").child(userid);

        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adminProduct();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        BtnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AdminAddProduct.class);
                startActivity(intent);
            }
        });
        return v;
    }
    private void adminProduct() {

        String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("ProductDetails").child(useridd);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                updateProductModelList.clear();
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    UpdateProductModel updateProductModel = snapshot1.getValue(UpdateProductModel.class);
                    updateProductModelList.add(updateProductModel);
                }
                adapter = new AdminProductAdapter(getContext(),updateProductModelList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}