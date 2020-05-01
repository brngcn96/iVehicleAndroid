package com.example.vehicle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vehicle.Api.Services.ApiClient;
import com.example.vehicle.Api.Services.UserService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentReport extends Fragment {
    private EditText edtReport;
    private TextView txtReport;
    private Button btnReport2;

    int x=5;
    Bundle bundle3=new Bundle();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.fragment_report_layout, container, false);



         Bundle bundle=getArguments();

        final String token=bundle.getString("token");
        final int userid=bundle.getInt("userid");
        final int rideid=bundle.getInt("rideid");
        edtReport = view.findViewById(R.id.edtReport);
        txtReport = view.findViewById(R.id.txtReport);
        btnReport2 = view.findViewById(R.id.btnReport2);

        btnReport2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               createRequest(token,userid,rideid,edtReport.getText().toString());

            }
        });
        return view;
    }

    private void createRequest(String token,int userid,int rideid,String description){
        UserService api = ApiClient.getClient().create(UserService.class);
        Call<ResponseBody> call = api.ReportVehicle(userid,rideid,description);

        call.enqueue(new Callback<ResponseBody>() {
            @Override

            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                   Toast.makeText(getActivity(), " Rapor gitti!!!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getActivity(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}
