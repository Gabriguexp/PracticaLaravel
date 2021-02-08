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

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.practicalaravel.R;
import com.example.practicalaravel.model.pojo.Admin;
import com.example.practicalaravel.viewmodel.ViewModel;

import java.util.ArrayList;

public class InicioFragment extends Fragment {

 Button btadmininicio, btusuarioinicio;
 NavController navController;
 ArrayList<Admin> adminList = new ArrayList();
 ViewModel viewModel;

    Dialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio_fragment, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        if(ViewModel.currentAdmin != null){
            ViewModel.currentAdmin = null;
        }
        navController = Navigation.findNavController(view);
        btadmininicio = view.findViewById(R.id.adminbtinicio);
        btusuarioinicio = view.findViewById(R.id.usuariobtinicio);
        viewModel.getAllAdmin();
        viewModel.getAdminLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Admin>>() {
            @Override
            public void onChanged(ArrayList<Admin> admins) {
                adminList.clear();
                adminList.addAll(admins);
            }
        });

        btadmininicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("xyz", "entro aqui");
                dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_admin);
            //    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.CENTER);

                EditText etUsuario = dialog.findViewById(R.id.usuarioet);
                EditText etPass = dialog.findViewById(R.id.contraseñaet);
                TextView tverrordialog = dialog.findViewById(R.id.tverrordialog);

                Button btAccesoAdmin = dialog.findViewById(R.id.btaccesoadmin);
                btAccesoAdmin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String usuario = etUsuario.getText().toString();
                        String pass = etPass.getText().toString();
                        Log.v("xyzuser", usuario);
                        Log.v("xyzpass", pass);
                        boolean logged = false;

                        for(Admin admin : adminList){
                            Log.v("xyz", admin.toString());
                            if(admin.getContraseña().equals(pass) && admin.getUsuario().equals(usuario)){
                                logged = true;
                                ViewModel.currentAdmin = admin;
                            }
                        }
                        if(logged){

                            Log.v("xyzcorrecto", "correcto");
                            tverrordialog.setText("Correcto");
                            tverrordialog.setTextColor(getResources().getColor(R.color.correct));
                            navController.navigate(R.id.action_InicioFragment_to_AdminFragment);
                            dialog.dismiss();
                        }else{
                            Log.v("xyzincorrecto", "incorrecto");
                            tverrordialog.setText("Usuario o contraseña incorrectos");
                            tverrordialog.setTextColor(getResources().getColor(R.color.design_default_color_error));
                        }
                    }
                });
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
                dialog.show();

            }
        });

        btusuarioinicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_InicioFragment_to_listaUserFragment);
            }
        });
    }
}