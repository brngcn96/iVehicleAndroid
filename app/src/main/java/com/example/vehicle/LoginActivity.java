package com.example.vehicle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vehicle.Api.Model.AuthorizedDto;
import com.example.vehicle.Api.Services.ApiClient;
import com.example.vehicle.Api.Services.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    public EditText txtEmail, txtPassword;
    public Button btnLogin2;
    public void init(){
        txtEmail = (EditText) findViewById(R.id.txtEmailLogin);
        txtPassword = (EditText) findViewById(R.id.txtPasswordLogin);
        btnLogin2 = (Button) findViewById(R.id.btnLogin2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void openAdultMenuActivity(View view) {
        init();
        loginUser();
    }

    public void loginUser() {
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"E-mail field cannot be blank !", Toast.LENGTH_LONG).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Password field cannot be blank !", Toast.LENGTH_LONG).show();
        }
        else{
            createRequest(email,password);
        }
    }

   private void createRequest(String email, String password) {
      UserService api = ApiClient.getClient().create(UserService.class);

        Call<AuthorizedDto> call = api.Login(email, password);
       call.enqueue(new Callback<AuthorizedDto>() {
         @Override public void onResponse(Call<AuthorizedDto> call, Response<AuthorizedDto> response) {
            if (response.isSuccessful()) {
                   AuthorizedDto user = response.body();
                     if(user!=null) {
                          try {
                              Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                               intent.putExtra("token", user.Token);
                               intent.putExtra("userid",user.getID());

                              startActivity(intent);

                           }catch (Exception e){
                               Toast.makeText(LoginActivity.this," Aktarım başarısız " + e.getMessage(), Toast.LENGTH_SHORT).show();
                          }
                      }
                      else{
                         Toast.makeText(LoginActivity.this, "hatalı giriş", Toast.LENGTH_SHORT).show();

                       }
             } else {
                  Toast.makeText(LoginActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
              }
           }
           @Override
         public void onFailure(Call call, Throwable t) {
                Toast.makeText(LoginActivity.this, "fail" , Toast.LENGTH_SHORT).show();

           }
      });
  }
}



