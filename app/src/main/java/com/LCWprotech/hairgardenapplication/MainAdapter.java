package com.LCWprotech.hairgardenapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.LCWprotech.hairgardenapplication.Customer.Customer;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    Activity activity;
    ArrayList<String> arrayList;

    public MainAdapter(Activity activity, ArrayList<String> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drawer_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(arrayList.get(position));

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();

                switch (position) {
                    case 0:
                        activity.startActivity(new Intent(activity, Customer.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        break;
                    case 1:
                        activity.startActivity(new Intent(activity,Appointment.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        break;
                    case 2:
                        activity.startActivity(new Intent(activity,Hairstyle.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        break;
                    case 3:
                        activity.startActivity(new Intent(activity,Product.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        break;
                    case 4:
                        activity.startActivity(new Intent(activity,About.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        break;
                    case 5:
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setTitle("Logout");
                        builder.setMessage("Are you sure you want to logout?");
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                activity.finishAffinity();
                                System.exit(0);
                            }
                        });
                        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.show();
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view);
        }
    }
}
