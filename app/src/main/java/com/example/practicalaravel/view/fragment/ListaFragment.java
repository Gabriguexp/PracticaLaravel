package com.example.practicalaravel.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.practicalaravel.R;
import com.example.practicalaravel.model.pojo.Consola;
import com.example.practicalaravel.view.adapter.RecyclerAdapter;
import com.example.practicalaravel.viewmodel.ViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListaFragment extends Fragment {

    private RecyclerView recyclerView;
    private ViewModel viewModel;
    ArrayList<Consola> lista = new ArrayList<>();
    TextView tverror;
    Button btvolverlista;
    NavController navController;
    public ListaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        recyclerView = view.findViewById(R.id.li);
        tverror = view.findViewById(R.id.tverror);
        btvolverlista = view.findViewById(R.id.btvolverlista);
        navController = Navigation.findNavController(view);
        btvolverlista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_listaFragment_to_inicioFragment);
            }
        });

        Call<ArrayList<Consola>> call = viewModel.getClient().getConsolas();
        call.enqueue(new Callback<ArrayList<Consola>>() {
            @Override
            public void onResponse(Call<ArrayList<Consola>> call, Response<ArrayList<Consola>> response) {
                lista = response.body();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.v("xyz", lista.toString());

                initRecycler(view);
            }

            @Override
            public void onFailure(Call<ArrayList<Consola>> call, Throwable t) {

            }
        });



    }
    private void initRecycler(View view) {
        try{
            ArrayList<Consola> newList = new ArrayList<>();

            for(Consola c: lista){
                newList.add(c);
                Log.v("xyzc", c.toString());
            }
            Log.v("zyx", "entro aqui");
            RecyclerAdapter adapter = new RecyclerAdapter(newList, getContext(), getView());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
            if(adapter.getItemCount() == 0){
                tverror.setText(R.string.tverrorempty);
            }

        }catch (NullPointerException ex){
            Log.v("xyz", ex.getLocalizedMessage());
        }

    }
}