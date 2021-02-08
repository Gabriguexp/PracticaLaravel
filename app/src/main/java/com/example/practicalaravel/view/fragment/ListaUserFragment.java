package com.example.practicalaravel.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class ListaUserFragment extends Fragment {

    private RecyclerView recyclerView;
    private ViewModel viewModel;
    ArrayList<Consola> lista = new ArrayList<>();
    TextView tverror;
    Button btvolverlista;
    NavController navController;

    public ListaUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(ViewModel.currentAdmin != null){
            ViewModel.currentAdmin = null;
        }

        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        recyclerView = view.findViewById(R.id.recycleruser);
        navController = Navigation.findNavController(view);
        initRecycler(view);

        btvolverlista = view.findViewById(R.id.volverlistauserbt);
        btvolverlista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_listaUserFragment_to_InicioFragment);
            }
        });
    }

    private void initRecycler(View view) {
        try{
            viewModel.getConsolas();
            ArrayList<Consola> newList = new ArrayList<>();
            RecyclerAdapter adapter = new RecyclerAdapter(newList, getContext(), getView());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

            viewModel.getMutableLiveData().observe(getActivity(), new Observer<ArrayList<Consola>>() {
                @Override
                public void onChanged(ArrayList<Consola> consolas) {
                    newList.clear();
                    newList.addAll(consolas);
                    adapter.notifyDataSetChanged();
                }
            });
        }catch (NullPointerException ex){
        }

    }
}