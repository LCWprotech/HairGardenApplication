package com.LCWprotech.hairgardenapplication.Admin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.LCWprotech.hairgardenapplication.Customer.AppointmentInfo;
import com.LCWprotech.hairgardenapplication.R;

import java.util.ArrayList;
import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder>{
    private Context mcont;
    private List<AppointmentModel> AppointmentModelList;

    public AppointmentAdapter(Context context , List<AppointmentModel>AppointmentModelList){
        this.AppointmentModelList = AppointmentModelList;
        this.mcont = context;
    }

    @NonNull
    @Override
    public AppointmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcont).inflate(R.layout.appointment_lv_item,parent,false);
        return new AppointmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentAdapter.ViewHolder holder, int position) {

        final AppointmentModel appointmentModel = AppointmentModelList.get(position);
        holder.name.setText(appointmentModel.getName());;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcont,UpdateDeleteAppointment.class);
                intent.putExtra("updatedeleteappointment",appointmentModel.getRandomUID());
                intent.putExtra("cusID",appointmentModel.getCusId());
                mcont.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return AppointmentModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.appointment_name);
        }
    }
}
