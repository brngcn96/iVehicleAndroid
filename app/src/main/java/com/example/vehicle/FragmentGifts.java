package com.example.vehicle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentGifts extends Fragment {
    public TextView txtGifts;
    public Button btnGifts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_gifts_layout, container, false);
        txtGifts = (TextView) view.findViewById(R.id.txtGifts);
        btnGifts  = (Button) view.findViewById(R.id.btnGifts);

        btnGifts.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               txtGifts.setText("Gifts from service"); //Hediyeleri goruntuleme servisi. Butona basıldıgında textte gifts goruntulenir.
            }
        });
        return view;
    }
}
