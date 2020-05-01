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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentLoadCredit extends Fragment {
    public TextView txtLoad, txtLoad2, txtLoad3;
    public Button btnForMe, btnForChild;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_loadcredit_layout, container, false);
        txtLoad = (TextView) view.findViewById(R.id.txtLoad);
        txtLoad2 = (TextView) view.findViewById(R.id.txtLoad2);
        txtLoad3 = (TextView) view.findViewById(R.id.txtLoad3);
        btnForMe  = (Button) view.findViewById(R.id.btnForMe);
        btnForChild  = (Button) view.findViewById(R.id.btnForChild);

        btnForMe.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                FragmentLoadCreditChild llf = new FragmentLoadCreditChild();
                ft.replace(R.id.fragment_tutucu, llf);
                ft.commit();
            }
        });

        btnForChild.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                FragmentLoadCreditMe llf = new FragmentLoadCreditMe();
                ft.replace(R.id.fragment_tutucu, llf);
                ft.commit();
            }
        });
        return view;
    }
}
