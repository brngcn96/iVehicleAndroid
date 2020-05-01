package com.example.vehicle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentLoadCreditMe extends Fragment {
    public TextView txtCreditMe;
    public EditText txtLoadCreditMe;
    public Button btnCreditMe;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_load_credit_me, container, false);
        txtCreditMe = (TextView) view.findViewById(R.id.txtCreditMe);
        txtLoadCreditMe = (EditText) view.findViewById(R.id.txtLoadCreditMe);
        btnCreditMe  = (Button) view.findViewById(R.id.btnCreditMe);

        btnCreditMe.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Kendine kredi yükleme web servisi.
            }
        });
        return view;
    }
}