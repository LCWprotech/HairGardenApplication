package com.LCWprotech.hairgardenapplication.Customer;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.LCWprotech.hairgardenapplication.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CustomerAppointmentFragment extends Fragment {

    ImageView btMenu;
    RecyclerView recyclerView;
    EditText date_in;
    EditText time_in;
    EditText date_time_in;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_customer_appointment,null);
        getActivity().setTitle("Appointment");
        date_in=v.findViewById(R.id.date_input);
        time_in=v.findViewById(R.id.time_input);
        date_time_in=v.findViewById(R.id.date_time_input);

        date_time_in.setInputType(InputType.TYPE_NULL);
        date_in.setInputType(InputType.TYPE_NULL);
        time_in.setInputType(InputType.TYPE_NULL);

        date_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(date_in);
            }
        });
        return v;
    }
    private void showDateDialog(final EditText date_in) {
        final Calendar mcurrentDate = Calendar.getInstance();
        mcurrentDate.add(Calendar.DAY_OF_MONTH, 1);
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog mDatePicker = new DatePickerDialog(
                getContext(), new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker,
                                  int selectedyear, int selectedmonth,
                                  int selectedday) {

                mcurrentDate.set(Calendar.YEAR, selectedyear);
                mcurrentDate.set(Calendar.MONTH, selectedmonth);
                mcurrentDate.set(Calendar.DAY_OF_MONTH,
                        selectedday);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

                date_in.setText(simpleDateFormat.format(mcurrentDate
                        .getTime()));
            }
        }, mYear, mMonth, mDay);
        mDatePicker.getDatePicker().setMinDate(mcurrentDate.getTimeInMillis());
        mDatePicker.show();

        //DatePickerDialog mDatePicker = new DatePickerDialog(getContext(),dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

    }
}