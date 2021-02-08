package com.example.practicalaravel.view.fragment;

import android.app.ActionBar;
import android.app.Dialog;
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

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practicalaravel.R;
import com.example.practicalaravel.model.pojo.Consola;
import com.example.practicalaravel.model.pojo.Reparacion;
import com.example.practicalaravel.view.adapter.ReparacionAdapter;
import com.example.practicalaravel.viewmodel.ViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class EditFragment extends Fragment {

    Consola consola;
    ViewModel viewModel;
    Button editBt, deletebt, volverbt, addReparacionBt;
    EditText nombreedit, urledit, estadoedit, fechaedit, precioedit;
    NavController navController;
    RecyclerView recyclerView;
    ArrayList<Reparacion> listaReparaciones = new ArrayList<>();
    Dialog dialog;

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
        if(!consola.getFechaventa().equalsIgnoreCase("null")){
            fechaedit.setText(consola.getFechaventa());
        }

        precioedit.setText(""+consola.getPrecio());


        editBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    consola.setPrecio(Double.parseDouble(precioedit.getText().toString()));
                    consola.setNombre(nombreedit.getText().toString());
                    consola.setUrlpic(urledit.getText().toString());
                    consola.setEstado(estadoedit.getText().toString());
                    consola.setFechaventa(fechaedit.getText().toString());
                    viewModel.editConsola(consola.getId(), consola);
                    navController.navigate(R.id.action_editFragment_to_listaAdminFragment);
                }catch (NumberFormatException exception){
                    Toast.makeText(getContext(), "Hay un problema con algun campo", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });

        deletebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.deleteConsola(consola.getId());
                navController.navigate(R.id.action_editFragment_to_listaAdminFragment);
            }
        });
        volverbt = view.findViewById(R.id.btvolveredit);
        volverbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_editFragment_to_listaAdminFragment);
            }
        });


        viewModel.getConsolaById(consola.getId());

        addReparacionBt = view.findViewById(R.id.addReparacionBt);
        addReparacionBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_reparacion);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.CENTER);

                EditText etDescReparacion = dialog.findViewById(R.id.etdescreparacion);
                EditText etPrecioReparacion = dialog.findViewById(R.id.etprecioreparacion);
                EditText etFechaReparacion = dialog.findViewById(R.id.etfechareparacion);
                Button btaddreparacion = dialog.findViewById(R.id.btaddreparacion);



                btaddreparacion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            String descripcion = etDescReparacion.getText().toString();
                            String fecha = etFechaReparacion.getText().toString();
                            double precio = Double.parseDouble(etPrecioReparacion.getText().toString());
                            //GUARDAR REPARACION
                            Reparacion reparacion = new Reparacion(ViewModel.current.getId(), descripcion, fecha ,precio);
                            viewModel.insertReparacion(reparacion);
                            viewModel.getAllReparaciones();
                        }catch (Exception ex){

                        }
                    }
                });
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
                dialog.show();
            }
        });
        initRecycler(view);
    }



    private void initRecycler(View view) {

        recyclerView = view.findViewById(R.id.recyclerreparaciones);
        ReparacionAdapter adapter= new ReparacionAdapter(listaReparaciones, getContext(), getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        viewModel.getAllReparaciones();
        viewModel.getReparacionLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Reparacion>>() {
            @Override
            public void onChanged(ArrayList<Reparacion> reparacions) {
                listaReparaciones.clear();
                for(Reparacion reparacion : reparacions){
                    if(reparacion.getIdconsola() == consola.getId()){
                        listaReparaciones.add(reparacion);
                    }
                }
                adapter.notifyDataSetChanged();

            }
        }) ;


    }

}