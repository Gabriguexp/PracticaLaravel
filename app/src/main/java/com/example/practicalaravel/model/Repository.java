package com.example.practicalaravel.model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.practicalaravel.model.pojo.Admin;
import com.example.practicalaravel.model.pojo.Consola;
import com.example.practicalaravel.model.pojo.Reparacion;
import com.example.practicalaravel.model.retrofit.AdminClient;
import com.example.practicalaravel.model.retrofit.ConsolaClient;
import com.example.practicalaravel.model.retrofit.ReparacionClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

public class Repository {
    public ConsolaClient consolaClient;
    public AdminClient adminClient;
    public ReparacionClient reparacionClient;
    private ArrayList<Consola> list;
    private MutableLiveData<ArrayList<Consola>> consolaLiveData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Admin>> adminLiveData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Reparacion>> reparacionLiveData = new MutableLiveData<>();

    public Repository(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/laravel/Practica3/public/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        consolaClient = retrofit.create(ConsolaClient.class);
        adminClient = retrofit.create(AdminClient.class);
        reparacionClient = retrofit.create(ReparacionClient.class);

    }

    public int[] deleteConsola(long id) {
        final int[] borrados = {0};
        Call<Integer> call = consolaClient.deleteConsola(id);
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
        Call<Consola> call = consolaClient.getConsolaById(id);
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

        Call<ArrayList<Consola>> call = consolaClient.getConsolas();
        call.enqueue(new Callback<ArrayList<Consola>>() {
            @Override
            public void onResponse(Call<ArrayList<Consola>> call, Response<ArrayList<Consola>> response) {
                consolaLiveData.setValue(response.body());

            }

            @Override
            public void onFailure(Call<ArrayList<Consola>> call, Throwable t) {
            }
        });

    }

    public void insertConsola(Consola consola) {
        Call<Long> call =consolaClient.insertConsola(consola);
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
         Call<Integer> call = consolaClient.updateConsola(id, consola);
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
        return consolaClient;
    }

    public MutableLiveData<ArrayList<Consola>> getConsolaLiveData() {
        return consolaLiveData;
    }

    public MutableLiveData<ArrayList<Admin>> getAdminLiveData() {
        return adminLiveData;
    }

    public void getAllAdmin(){
        Call<ArrayList<Admin>> call = adminClient.getAdmin();
        call.enqueue(new Callback<ArrayList<Admin>>() {
            @Override
            public void onResponse(Call<ArrayList<Admin>> call, Response<ArrayList<Admin>> response) {
                adminLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Admin>> call, Throwable t) {
                Log.v("xyzfail", t.getLocalizedMessage());
            }
        });
    }

    public void deleteAdmin(long id) {
        Call<Integer> call = adminClient.deleteAdmin(id);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.v("xyz", "Borrado: "+response.body());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.v("xyz", "error Borrar: "+t.getLocalizedMessage());
            }
        });
    }

    public void insertAdmin(Admin admin){
        Call<Long> call = adminClient.insertAdmin(admin);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.v("xyz", response.body().toString());
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.v("xyz", t.getLocalizedMessage());
            }
        });
    }

    public void insertReparacion(Reparacion reparacion){
        Call<Long> call = reparacionClient.insertReparacion(reparacion);
        call.enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                Log.v("xyz", "exito: "+response.code());
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {
                Log.v("xyz", t.getLocalizedMessage());
            }
        });
    }

    public void updateReparacion(long id, Reparacion reparacion){
        Call<Integer> call = reparacionClient.updateReparacion(id, reparacion);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {

            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });

    }

    public void getAllReparaciones(){
        Call<ArrayList<Reparacion>> call = reparacionClient.getReparaciones();
        call.enqueue(new Callback<ArrayList<Reparacion>>() {
            @Override
            public void onResponse(Call<ArrayList<Reparacion>> call, Response<ArrayList<Reparacion>> response) {
                reparacionLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Reparacion>> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<ArrayList<Reparacion>> getReparacionLiveData() {
        return reparacionLiveData;
    }
}
