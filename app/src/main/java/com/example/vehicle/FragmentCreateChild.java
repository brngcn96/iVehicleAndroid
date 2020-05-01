package com.example.vehicle;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.vehicle.Api.Services.ApiClient;
import com.example.vehicle.Api.Services.UserService;
import com.example.vehicle.R;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCreateChild extends Fragment {
    public EditText txtUsernameChild, txtPasswordChild;
    public Button btnRegister3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_createchild_layout, container, false);
        txtUsernameChild = (EditText) view.findViewById(R.id.txtUsernameChild);
        txtPasswordChild = (EditText) view.findViewById(R.id.txtPasswordChild);
        btnRegister3  = (Button) view.findViewById(R.id.btnRegister3);

        btnRegister3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                createNewAccount();
            }
        });
        return view;
    }

    private void createNewAccount() {
        String username = txtUsernameChild.getText().toString();
        String password = txtPasswordChild.getText().toString();

        if (TextUtils.isEmpty(username))
            Toast.makeText(getActivity(),"Username field cannot be blank !",Toast.LENGTH_LONG).show();
        else if (TextUtils.isEmpty(password))
            Toast.makeText(getActivity(),"Password field cannot be blank!",Toast.LENGTH_LONG).show();
        else
        {}



    }

    private void createRequest(String name,String surname,String email,String password){
        UserService api = ApiClient.getClient().create(UserService.class);
        Call<ResponseBody> call = api.RegisterUser(name, surname, email, password);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Child account has been created", Toast.LENGTH_SHORT).show();

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
