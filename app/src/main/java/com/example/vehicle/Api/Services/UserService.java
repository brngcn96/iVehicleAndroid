package com.example.vehicle.Api.Services;

import com.example.vehicle.Api.Model.AuthorizedDto;
import com.example.vehicle.Api.Model.AvaiblesDto;
import com.example.vehicle.Api.Model.Post;
import com.example.vehicle.Api.Model.RideDto;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService { //POST, GET, DELETE, PUT METOTLARI BURADA OLCAK4
    //@GET("getUsers"); //ORNEK FONKSİYON İSMİ BUNUN GİBİ SEYLER
    // Call<List<User>> getUsers; //BURADA LISTE SEKLİNDE GELİCEK SEYLER CEKİLCEK MESELE RİDELARI CEKERİZ

    //TUM KAYITLARIN GETİRİLMESİ İCİN MAIN CLASSTA USER SERVİCE İLE NESNE OLUSTURUP getClient() CAGIRCAZ
    //APİ SERVİSİNE BAGLNMIS OLCAZ
    //SU AN KAYIT OLMADIGI ICIN BU ASAMAYI YAPMADIM SONRA YAPARIM

    @POST("api/User/RegisterUser")
    Call<ResponseBody> RegisterUser(@Query("name") String name, @Query("surname") String surname, @Query("email") String email, @Query("password") String password); //user gönderme işlemi


    @GET("api/Authanticate/Login")
    Call<AuthorizedDto> Login(@Query("email") String email, @Query("password") String password );

    @POST("api/User/CreateRide")
    Call<RideDto> CreateRide(@Header("Authorization") String token ,@Query("id") int id );

    @GET("/posts")
    Call<List<Post>> getPosts();
    @GET("Admins/Delete/{id}")
    Call<ResponseBody> delete(@Path("id") int id);

    @GET("api/User/IsVehicleReady")
    Call<Integer> IsVehicleReady(@Header("Authorization") String token, @Query("station") int station,@Query("position")int position);

    @POST("api/User/ReportVehicle")
    Call<ResponseBody> ReportVehicle(@Query("userid")int userid,@Query("rideid")int rideid,@Query("description") String description);

    }
