package com.LCWprotech.hairgardenapplication.Staff;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.LCWprotech.hairgardenapplication.Admin.UpdateProductModel;
import com.LCWprotech.hairgardenapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView productRecyclerView;
    Animation animation;
    private List<UpdateProductModel> updateProductModelList;
    private ProductAdapter adapter;
    DatabaseReference dataa, databaseReference;
    SwipeRefreshLayout swipeRefreshLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_product, null);
        getActivity().setTitle("Product");
        productRecyclerView = v.findViewById(R.id.recycle_menu);
        productRecyclerView.setHasFixedSize(true);
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.move);
        productRecyclerView.startAnimation(animation);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        updateProductModelList = new ArrayList<>();
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipelayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark, R.color.Red);

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                String userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                dataa = FirebaseDatabase.getInstance().getReference("Staff").child(userid);
                dataa.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        customermenu();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        return v;
    }
        @Override
        public void onRefresh () {
            customermenu();
        }

        private void customermenu () {

            swipeRefreshLayout.setRefreshing(true);
            databaseReference = FirebaseDatabase.getInstance().getReference("ProductDetails");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    updateProductModelList.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                            UpdateProductModel updateProductModel = snapshot1.getValue(UpdateProductModel.class);
                            updateProductModelList.add(updateProductModel);
                        }
                    }
                    adapter = new ProductAdapter(getContext(), updateProductModelList);
                    productRecyclerView.setAdapter(adapter);
                    swipeRefreshLayout.setRefreshing(false);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
    }


}
