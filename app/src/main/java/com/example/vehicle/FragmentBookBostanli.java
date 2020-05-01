package com.example.vehicle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.vehicle.Api.Model.AvaiblesDto;
import com.example.vehicle.Api.Model.RideDto;
import com.example.vehicle.Api.Services.ApiClient;
import com.example.vehicle.Api.Services.UserService;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBookBostanli extends Fragment {
    private Button vehicle1, vehicle2, vehicle3, vehicle4, vehicle5, vehicle6, vehicle7, vehicle8, vehicle9;
    Bundle bundle1 = new Bundle();
    Bundle bundle7=new Bundle();
    int result;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_bostanli_layout, container, false);

        Bundle bundle = getArguments();

        final String token = bundle.getString("token");
        final int userid = bundle.getInt("userid");

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
            public void onClick(View v) {
                Request(token,1,1,userid);
            }
        });

        vehicle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request(token,1,2,userid);
            }
        });
        vehicle3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request(token,1,3,userid);
            }
        });
        vehicle4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request(token,1,4,userid);

            }
        });
        vehicle5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request(token,1,5,userid);
            }
        });
        vehicle6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request(token,1,6,userid);
            }
        });




        return view;
    }

    private void Request(final String token, int stationid, final int position, final int userid) {


        UserService api = ApiClient.getClient().create(UserService.class);

        Call<Integer> call = api.IsVehicleReady(token,stationid,position);
        call.enqueue(new Callback<Integer>() {
            public void onResponse(Call<Integer> call, Response<Integer> response) {

                if (response.isSuccessful()) {
                    if (response.body() == 0 || response.body()==-1){


                            switch(position){

                                case 1:
                                    vehicle1.setBackgroundColor(Color.RED);
                                    Toast.makeText(getActivity(), "Araç Müsait Değil!", Toast.LENGTH_SHORT).show();

                                    break;
                                case 2:
                                    vehicle2.setBackgroundColor(Color.RED);
                                    Toast.makeText(getActivity(), "Araç Müsait Değil!", Toast.LENGTH_SHORT).show();

                                    break;
                                case 3:
                                    vehicle3.setBackgroundColor(Color.RED);
                                    Toast.makeText(getActivity(), "Araç Müsait Değil!", Toast.LENGTH_SHORT).show();

                                    break;
                                case 4:
                                    vehicle4.setBackgroundColor(Color.RED);
                                    Toast.makeText(getActivity(), "Araç Müsait Değil!", Toast.LENGTH_SHORT).show();

                                    break;
                                case 5:
                                    vehicle5.setBackgroundColor(Color.RED);
                                    Toast.makeText(getActivity(), "Araç Müsait Değil!", Toast.LENGTH_SHORT).show();

                                    break;
                                case 6:
                                    vehicle6.setBackgroundColor(Color.RED);
                                    Toast.makeText(getActivity(), "Araç Müsait Değil!", Toast.LENGTH_SHORT).show();

                                    break;
                                case 7:
                                    vehicle7.setBackgroundColor(Color.RED);
                                    Toast.makeText(getActivity(), "Araç Müsait Değil!", Toast.LENGTH_SHORT).show();

                                    break;
                                case 8:
                                    vehicle8.setBackgroundColor(Color.RED);
                                    Toast.makeText(getActivity(), "Araç Müsait Değil!", Toast.LENGTH_SHORT).show();

                                    break;
                                case 9:
                                    vehicle9.setBackgroundColor(Color.RED);
                                    Toast.makeText(getActivity(), "Araç Müsait Değil!", Toast.LENGTH_SHORT).show();

                                    break;
                                default:
                                    break;
                            }

                    }
                    else if(response.body()==1){


                        createRequest(userid,token);


                    }
                    else{}



                }
                else {
                    Toast.makeText(getActivity(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getActivity(), "fail" , Toast.LENGTH_SHORT).show();

            }
        });

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
