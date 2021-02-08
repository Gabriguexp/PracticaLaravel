package com.example.practicalaravel.view.fragment;

import android.app.ActionBar;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
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

import com.example.practicalaravel.R;
import com.example.practicalaravel.model.pojo.Admin;
import com.example.practicalaravel.model.pojo.Consola;
import com.example.practicalaravel.view.adapter.AdapterAdmin;
import com.example.practicalaravel.view.adapter.RecyclerAdapter;
import com.example.practicalaravel.viewmodel.ViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class AdminAdminFragment extends Fragment {

    RecyclerView recyclerView;
    ViewModel viewModel;
    Button btsalirAdmin;
    FloatingActionButton fabadmin;
    NavController navController;
    Dialog dialog;
    ArrayList<Admin> newList = new ArrayList<>();
    public AdminAdminFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_admin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(ViewModel.class);
        recyclerView = view.findViewById(R.id.recyclerAdmins);
        navController = Navigation.findNavController(view);
        btsalirAdmin = view.findViewById(R.id.salirlistaadminbt);
        btsalirAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_adminAdminFragment_to_AdminFragment);
            }
        });

        fabadmin = view.findViewById(R.id.fabadmin);
        fabadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_insert_admin);
             //   dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.CENTER);

                EditText etadduser = dialog.findViewById(R.id.usuarioaddadminet);
                EditText etaddPass = dialog.findViewById(R.id.contraseñaaddadminet);
                TextView tverroradd = dialog.findViewById(R.id.tverroradddialog);
                Button btaddadmin = dialog.findViewById(R.id.btaddadmin);
                btaddadmin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String user = etadduser.getText().toString();
                        String pass = etaddPass.getText().toString();
                        if (user.isEmpty() || pass.isEmpty()) {
                            tverroradd.setTextColor(getResources().getColor(R.color.design_default_color_error));
                            tverroradd.setText("Debes rellenar todos los campos");
                        } else if(adminRepetido(user)){
                            tverroradd.setTextColor(getResources().getColor(R.color.design_default_color_error));
                            tverroradd.setText("Ese usuario para la cuenta de administrador no esta disponible");
                        } else {
                            viewModel.insertAdmin(new Admin(user, pass));
                            tverroradd.setText("Admin insertado con exito");
                            tverroradd.setTextColor(getResources().getColor(R.color.correct));
                            viewModel.getAllAdmin();

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
        try{
            viewModel.getAllAdmin();

            AdapterAdmin adapter = new AdapterAdmin(newList, getActivity());

            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

            viewModel.getAdminLiveData().observe(getActivity(), new Observer<ArrayList<Admin>>() {
                @Override
                public void onChanged(ArrayList<Admin> admins) {
                    newList.clear();
                    for(Admin a: admins ){
                        if(a.getId()!=1) {
                            newList.add(a);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            });
        }catch (NullPointerException ex){
            Log.v("xyz", ex.getLocalizedMessage());
        }
    }


    private boolean adminRepetido(String user) {
        for(Admin a : newList){
            if(a.getUsuario().equals(user) ){
                return true;
            }
        }
        return false;
    }
}