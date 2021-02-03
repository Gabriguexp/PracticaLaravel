package com.example.practicalaravel.model.retrofit;

import com.example.practicalaravel.model.pojo.Consola;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ConsolaClient {

    @DELETE("consola/{id}")
    Call<Integer> deleteConsola(@Path("id") long id);

    @GET("consola/{id}")
    Call<Consola> getConsolaById(@Path("id") long id);

    @GET("consola")
    Call<ArrayList<Consola>> getConsolas();

    @POST("consola")
    Call<Long> insertConsola(@Body Consola consola);

    @PUT("consola/{id}")
    Call<Integer> updateConsola(@Path("id") long id, @Body Consola consola);
}
