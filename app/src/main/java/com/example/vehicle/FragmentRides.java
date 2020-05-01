package com.example.vehicle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentRides extends Fragment {
    public TextView txtRides;
    public Button btnRides;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_rides_layout, container, false);
        txtRides = (TextView) view.findViewById(R.id.txtRides);
        btnRides  = (Button) view.findViewById(R.id.btnRides);

        btnRides.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Eski sürüşleri goruntuleme servisi. Butona basıldıgında textte rides goruntulenir.
                txtRides.setText("Old rides from service : ");
            }
        });
        return view;
    }
}
