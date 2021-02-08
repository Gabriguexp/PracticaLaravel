package com.example.practicalaravel.model.retrofit;

import com.example.practicalaravel.model.pojo.Admin;
import com.example.practicalaravel.model.pojo.Consola;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AdminClient {

    @DELETE("admin/{id}")
    Call<Integer> deleteAdmin(@Path("id") long id);

    @GET("admin/{id}")
    Call<Admin> getAdminById(@Path("id") long id);

    @GET("admin")
    Call<ArrayList<Admin>> getAdmin();

    @POST("admin")
    Call<Long> insertAdmin(@Body Admin admin);

    @PUT("admin/{id}")
    Call<Integer> updateAdmin(@Path("id") long id, @Body Admin admin);

}
