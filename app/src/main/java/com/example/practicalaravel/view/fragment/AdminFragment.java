package com.example.practicalaravel.view.fragment;

import android.opengl.Visibility;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.practicalaravel.R;
import com.example.practicalaravel.viewmodel.ViewModel;


public class AdminFragment extends Fragment {

    Button bt1, bt2, btAdminAdmin, btVolver;
    NavController navController;


    public AdminFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        bt1 = view.findViewById(R.id.button);
        bt2 = view.findViewById(R.id.button2);
        btAdminAdmin = view.findViewById(R.id.btadminadmin);
        btVolver = view.findViewById(R.id.btvolvermenu);

        if(ViewModel.currentAdmin.getId() !=1){
            btAdminAdmin.setVisibility(View.INVISIBLE);
        }

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_AdminFragment_to_addFragment);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_AdminFragment_to_listaAdminFragment);
            }
        });
        btAdminAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_AdminFragment_to_adminAdminFragment);
            }
        });

        btVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_AdminFragment_to_InicioFragment);
            }
        });

    }
}