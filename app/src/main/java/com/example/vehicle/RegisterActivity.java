package com.example.vehicle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vehicle.Api.Services.ApiClient;
import com.example.vehicle.Api.Services.UserService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    public EditText txtName, txtSurname, txtEmail, txtPassword;
    public Button btnRegister2;

    //public TextView textViewResult;
    public void init() {
        txtName = (EditText) findViewById(R.id.txtName);
        txtSurname = (EditText) findViewById(R.id.txtSurname);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnRegister2 = (Button) findViewById(R.id.btnRegister2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void openAdultMenuActivity2(View view) {
        init();
        createNewAccount();
    }

    private void createNewAccount() {
        String name = txtName.getText().toString();
        String surname = txtSurname.getText().toString();
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Name field cannot be blank !", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(surname)) {
            Toast.makeText(this, "Surname field cannot be blank !", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "E-mail field cannot be blank !", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Password field cannot be blank !", Toast.LENGTH_LONG).show();
        } else {
           try{
                init();
               createRequest(name,surname,email, password);
           }
           catch (Exception e){
               Toast.makeText(RegisterActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();

            }
       }

        }

        private void createRequest(String name,String surname,String email,String password){
            UserService api = ApiClient.getClient().create(UserService.class);
            Call<ResponseBody> call = api.RegisterUser(name, surname, email, password);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        Intent intent = new Intent(RegisterActivity.this, MainMenuActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }


}





