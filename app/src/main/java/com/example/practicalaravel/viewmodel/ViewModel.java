package com.example.practicalaravel.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.practicalaravel.model.Repository;
import com.example.practicalaravel.model.pojo.Consola;
import com.example.practicalaravel.model.retrofit.ConsolaClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class ViewModel extends AndroidViewModel {
    private Repository repository;
    public static Consola current;

    public ViewModel(@NonNull Application application){
        super(application);
        repository = new Repository();
    }



    public int[] deleteConsola(long id) {
        return repository.deleteConsola(id);
    }

    public void getConsolaById(long id) {
        repository.getConsolaById(id);
    }

    public void getConsolas() {
        repository.getConsolas();
    }

    public void insertConsola(Consola consola) {
        repository.insertConsola(consola);
    }


    public ArrayList<Consola> getList() {
        return repository.getList();
    }

    public ConsolaClient getClient() {
        return repository.getClient();
    }

    public void editConsola(long id, Consola consola) {
        repository.editConsola(id, consola);
    }

    public MutableLiveData<ArrayList<Consola>> getMutableLiveData() {
        return repository.getMutableLiveData();
    }
}
