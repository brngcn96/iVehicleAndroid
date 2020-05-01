package com.example.vehicle;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentBookGoztepe extends Fragment {
    private Button vehicle1, vehicle2, vehicle3, vehicle4, vehicle5, vehicle6, vehicle7, vehicle8, vehicle9;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.fragment_book_goztepe_layout, container, false);

        vehicle1 = (Button) view.findViewById(R.id.vehicle1);
        vehicle2 = (Button) view.findViewById(R.id.vehicle2);
        vehicle3 = (Button) view.findViewById(R.id.vehicle3);
        vehicle4 = (Button) view.findViewById(R.id.vehicle4);
        vehicle5 = (Button) view.findViewById(R.id.vehicle5);
        vehicle6 = (Button) view.findViewById(R.id.vehicle6);
        vehicle7 = (Button) view.findViewById(R.id.vehicle7);
        vehicle8 = (Button) view.findViewById(R.id.vehicle8);
        vehicle9 = (Button) view.findViewById(R.id.vehicle9);

        vehicle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                vehicle1.setBackgroundColor(Color.RED);
            }
        });

        vehicle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                vehicle2.setBackgroundColor(Color.RED);
            }
        });

        vehicle3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                vehicle3.setBackgroundColor(Color.RED);
            }
        });

        vehicle4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                vehicle4.setBackgroundColor(Color.RED);
            }
        });

        vehicle5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                vehicle5.setBackgroundColor(Color.RED);
            }
        });

        vehicle6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                vehicle6.setBackgroundColor(Color.RED);
            }
        });

        vehicle7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                vehicle7.setBackgroundColor(Color.RED);
            }
        });

        vehicle8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                vehicle8.setBackgroundColor(Color.RED);
            }
        });

        vehicle9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                vehicle9.setBackgroundColor(Color.RED);
            }
        });
        return view;
    }
}