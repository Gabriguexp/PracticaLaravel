package com.example.practicalaravel.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.practicalaravel.model.pojo.Consola;
import com.example.practicalaravel.model.retrofit.ConsolaClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    public ConsolaClient client;
    private ArrayList<Consola> list;
    private MutableLiveData<ArrayList<Consola>> mutableLiveData = new MutableLiveData<>();
    public Repository(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/laravel/Practica3/public/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        client = retrofit.create(ConsolaClient.class);
    }

    public int[] deleteConsola(long id) {
        final int[] borrados = {0};
        Call<Integer> call = client.deleteConsola(id);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                borrados[0]++;
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                borrados[0]= -1;
            }
        });
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return borrados;
    }

    public void getConsolaById(long id) {
        Call<Consola> call = client.getConsolaById(id);
        call.enqueue(new Callback<Consola>() {
            @Override
            public void onResponse(Call<Consola> call, Response<Consola> response) {

            }

            @Override
            public void onFailure(Call<Consola> call, Throwable t) {

            }
        });

    }

    public void getConsolas() {

        Call<ArrayList<Consola>> call = client.getConsolas();
        call.enqueue(new Callback<ArrayList<Consola>>() {
            @Override
            public void onResponse(Call<ArrayList<Consola>> call, Response<ArrayList<Consola>> response) {
                mutableLiveData.setValue(response.body());

            }

            @Override
            public void onFailure(Call<ArrayList<Consola>> call, Throwable t) {
            }
        });

    }

    public void insertConsola(Consola consola) {
        Call<Long> call =client.insertConsola(consola);
        call.enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {

            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {

            }
        });
    }

    public void editConsola(long id, Consola consola) {
         Call<Integer> call = client.updateConsola(id, consola);
         call.enqueue(new Callback<Integer>() {
             @Override
             public void onResponse(Call<Integer> call, Response<Integer> response) {

             }

             @Override
             public void onFailure(Call<Integer> call, Throwable t) {

             }
         });
    }

    public ArrayList<Consola> getList() {
        return list;
    }

    public ConsolaClient getClient() {
        return client;
    }

    public MutableLiveData<ArrayList<Consola>> getMutableLiveData() {
        return mutableLiveData;
    }
}
