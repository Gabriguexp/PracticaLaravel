package com.example.practicalaravel.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practicalaravel.R;
import com.example.practicalaravel.model.pojo.Consola;
import com.example.practicalaravel.viewmodel.ViewModel;

import java.time.LocalDate;


public class AddFragment extends Fragment {

    TextView tvnombreadd, tvurladd, tvestadoadd, tvprecioadd, tvfechaadd;
    Button btadd, btvolveradd;
    ViewModel viewModel;
    NavController navController;
    public AddFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btadd = view.findViewById(R.id.btadd);
        tvurladd = view.findViewById(R.id.tvurladd);
        tvnombreadd = view.findViewById(R.id.tvnombreadd);
        tvestadoadd = view.findViewById(R.id.tvestadoadd);
        tvprecioadd = view.findViewById(R.id.tvprecioadd);
        tvfechaadd = view.findViewById(R.id.tvfechaadd);
        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        btvolveradd = view.findViewById(R.id.btvolveradd);
        navController = Navigation.findNavController(view);
        btvolveradd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_addFragment_to_inicioFragment);

            }
        });


        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = tvurladd.getText().toString();
                String nombre = tvnombreadd.getText().toString();
                String estado = tvestadoadd.getText().toString();
                double precio = Double.parseDouble(tvprecioadd.getText().toString());
                String fecha = tvfechaadd.getText().toString();
                if((url.isEmpty()) || nombre.isEmpty() || estado.isEmpty() || precio == 0 ){
                    Toast.makeText(getContext(), "Los campos con asterisco no pueden estar vacios", Toast.LENGTH_LONG).show();
                } else{
                    Consola c = new Consola();
                    c.setNombre(nombre);
                    c.setEstado(estado);
                    c.setPrecio(precio);
                    c.setUrlpic(url);
                    if(!fecha.isEmpty()){
                        c.setFechaventa(fecha);
                    } else{
                        c.setFechaventa("null");
                    }
                    viewModel.insertConsola(c);
                    Toast.makeText(getContext(), "Consola insertada con exito", Toast.LENGTH_LONG).show();
                }

            }
        });




    }
}