package com.example.practicalaravel.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.practicalaravel.R;
import com.example.practicalaravel.model.pojo.Consola;
import com.example.practicalaravel.viewmodel.ViewModel;


public class EditFragment extends Fragment {

    Consola consola;
    ViewModel viewModel;
    Button editBt, deletebt, volverbt;
    EditText nombreedit, urledit, estadoedit, fechaedit, precioedit;
    NavController navController;
    public EditFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        navController = Navigation.findNavController(view);
        consola = viewModel.current;
        editBt = view.findViewById(R.id.btedit);
        nombreedit = view.findViewById(R.id.tvnombreedit);
        estadoedit = view.findViewById(R.id.tvestadoedit);
        urledit = view.findViewById(R.id.tvurledit);
        fechaedit = view.findViewById(R.id.tvfechaedit);
        precioedit = view.findViewById(R.id.tvprecioedit);
        deletebt = view.findViewById(R.id.btdelete);

        nombreedit.setText(consola.getNombre());
        estadoedit.setText(consola.getEstado());
        urledit.setText(consola.getUrlpic());
        fechaedit.setText(consola.getFechaventa());
        precioedit.setText(""+consola.getPrecio());


        editBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consola.setNombre(nombreedit.getText().toString());
                consola.setUrlpic(urledit.getText().toString());
                consola.setEstado(estadoedit.getText().toString());
                consola.setPrecio(Double.parseDouble(precioedit.getText().toString()));
                consola.setFechaventa(fechaedit.getText().toString());
                viewModel.editConsola(consola.getId(), consola);

                navController.navigate(R.id.action_editFragment_to_listaFragment);


            }
        });

        deletebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.deleteConsola(consola.getId());
                navController.navigate(R.id.action_editFragment_to_listaFragment);
            }
        });
        volverbt = view.findViewById(R.id.btvolveredit);
        volverbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_editFragment_to_listaFragment);
            }
        });
    }

}