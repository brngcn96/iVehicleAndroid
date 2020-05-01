package com.example.vehicle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentLoadCreditChild extends Fragment {
    public TextView txtCreditChild;
    public EditText txtLoadCreditChild;
    public Button btnCreditChild;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_load_credit_child, container, false);
        txtCreditChild = (TextView) view.findViewById(R.id.txtCreditChild);
        txtLoadCreditChild = (EditText) view.findViewById(R.id.txtLoadCreditChild);
        btnCreditChild  = (Button) view.findViewById(R.id.btnCreditChild);

        btnCreditChild.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Cocuga kredi yükleme web servisi.
                //successful durumunda asağıda toast message. Total crediyi gosterir.
                Toast.makeText(getContext(), "Credit Loaded! Total Credit : ", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}