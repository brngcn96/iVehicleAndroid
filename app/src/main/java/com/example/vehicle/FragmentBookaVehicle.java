package com.example.vehicle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.vehicle.Api.Model.AuthorizedDto;
import com.example.vehicle.Api.Services.ApiClient;
import com.example.vehicle.Api.Services.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBookaVehicle extends Fragment {
    public TextView txtBook1, txtBook2, txtBook3;
    public Button btnKonak, btnGoztepe, btnBostanli;
    int result=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final Bundle bundle=getArguments();

        final String token=bundle.getString("token");
        final int userid=bundle.getInt("userid");
        View view =inflater.inflate(R.layout.fragment_bookavehicle_layout, container, false);
        txtBook1 = (TextView) view.findViewById(R.id.txtBook1);
        txtBook2 = (TextView) view.findViewById(R.id.txtBook2);
        txtBook3 = (TextView) view.findViewById(R.id.txtBook3);
        btnKonak  = (Button) view.findViewById(R.id.btnKonak);
        btnGoztepe  = (Button) view.findViewById(R.id.btnGoztepe);
        btnBostanli  = (Button) view.findViewById(R.id.btnBostanli);

        btnKonak.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                FragmentBookKonak llf = new FragmentBookKonak();
                ft.replace(R.id.fragment_tutucu, llf);
                ft.commit();
            }
        });

        btnGoztepe.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                FragmentBookGoztepe llf = new FragmentBookGoztepe();
                ft.replace(R.id.fragment_tutucu, llf);
                ft.commit();
            }
        });

        btnBostanli.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                FragmentBookBostanli llf = new FragmentBookBostanli();
                bundle.putString("token",token);
                bundle.putInt("userid", userid);
                llf.setArguments(bundle);
                ft.replace(R.id.fragment_tutucu, llf);
                ft.commit();
            }
        });
        return view;
    }


}


