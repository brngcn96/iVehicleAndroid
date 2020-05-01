package com.example.vehicle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.vehicle.Api.Model.RideDto;
import com.example.vehicle.Api.Services.ApiClient;
import com.example.vehicle.Api.Services.UserService;
import com.example.vehicle.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentRent extends Fragment {
    public Button btnScan;

    Bundle bundle1=new Bundle();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_rent_layout, container, false);
        btnScan  = (Button) view.findViewById(R.id.btnScan);


        Bundle bundle=getArguments();

         final String token=bundle.getString("token");
        final int userid=bundle.getInt("userid");


        //Toast.makeText(getActivity(),x, Toast.LENGTH_SHORT).show();


        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                IntentIntegrator integrator = new IntentIntegrator(getActivity());
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
                 */
                createRequest(userid,token);

            }
        });
        return view;




    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(getActivity(),"User cancelled the scanning !",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getActivity(),result.getContents(),Toast.LENGTH_LONG).show();
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void createRequest(int id, final String token) {
        UserService api = ApiClient.getClient().create(UserService.class);

        Call<RideDto> call = api.CreateRide(token,id);
        call.enqueue(new Callback<RideDto>() {
            @Override
            public void onResponse(Call<RideDto> call, Response<RideDto> response) {
                if (response.isSuccessful()) {

                    RideDto newRide = response.body();

                    if (newRide != null) {
                        Toast.makeText(getActivity(), "sürüş başlangıç zamanı :  " +newRide.StartTime, Toast.LENGTH_SHORT).show();
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        FragmentDisplayRide llf = new FragmentDisplayRide();
                        bundle1.putString("token",token);
                        bundle1.putInt("userid", newRide.User_ID);
                        bundle1.putInt("rideid",newRide.Ride_ID);
                        llf.setArguments(bundle1);
                        ft.replace(R.id.fragment_tutucu, llf);

                        ft.commit();

                    } else {
                        Toast.makeText(getActivity(), "sürüş başlamadı", Toast.LENGTH_SHORT).show();

                    }
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage() ,Toast.LENGTH_LONG).show();
            }
        });
    }


}
