package com.LCWprotech.hairgardenapplication.Customer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.LCWprotech.hairgardenapplication.Login;
import com.LCWprotech.hairgardenapplication.R;

public class CustomerAboutFragment extends Fragment {

    ListView list;

    String[] maintitle ={
            "About Us","About Developer","Contact Us",
            "Feedback","Terms of Use","Privacy Policy",
            "App Version: Alpha 0.5.0",
    };

    Integer[] imgid={
            R.drawable.app_logo,R.drawable.team_logo,R.drawable.ic_baseline_email_24,
            R.drawable.ic_feedback_24,R.drawable.ic_policy_24,R.drawable.ic_policy_24,
            R.drawable.ic_apps_24,
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_customer_about,null);
        getActivity().setTitle("About");

        AboutListAdapter adapter=new AboutListAdapter(CustomerAboutFragment.this, maintitle, imgid);
        list=(ListView) v.findViewById(R.id.aboutList);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // TODO Auto-generated method stub
                if(position == 0) {
                    Intent intent= new Intent(getContext(),AboutUs.class);
                    startActivity(intent);
                }

                else if(position == 1) {
                    Intent intent= new Intent(getContext(),AboutDeveloper.class);
                    startActivity(intent);
                }

                else if(position == 2) {
                    Intent intent= new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("mailto:lcwprotech@gmail.com"));
                    startActivity(intent);
                }
                else if(position == 3) {
                    Intent intent= new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://forms.gle/h3wrmuq1GeZFu3797"));
                    startActivity(intent);
                }
                else if(position == 4) {
                    Intent intent= new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://hair-garden.flycricket.io/terms.html"));
                    startActivity(intent);
                }
                else if(position == 5) {
                    Intent intent= new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://hair-garden.flycricket.io/privacy.html"));
                    startActivity(intent);
                }
                else if(position == 6) {
                    Toast.makeText(getContext(), "You are up to date!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return v;
    }
}