package com.example.practicalaravel.model.retrofit;

import com.example.practicalaravel.model.pojo.Reparacion;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ReparacionClient {
    @DELETE("reparacion/{id}")
    Call<Integer> deleteReparacion(@Path("id") long id);

    @GET("reparacion/{id}")
    Call<Reparacion> getReparacionById(@Path("id") long id);

    @GET("reparacion")
    Call<ArrayList<Reparacion>> getReparaciones();

    @POST("reparacion")
    Call<Long> insertReparacion(@Body Reparacion reparacion);

    @PUT("reparacion/{id}")
    Call<Integer> updateReparacion(@Path("id") long id, @Body Reparacion reparacion);

}
