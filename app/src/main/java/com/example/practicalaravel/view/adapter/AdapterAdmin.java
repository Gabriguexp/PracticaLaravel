package com.example.practicalaravel.view.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicalaravel.R;
import com.example.practicalaravel.model.pojo.Admin;
import com.example.practicalaravel.viewmodel.ViewModel;

import java.util.ArrayList;

public class AdapterAdmin extends RecyclerView.Adapter<AdapterAdmin.ViewHolder> {
    ArrayList<Admin> adminlist;
    Activity activity;
    ViewModel viewModel;
    public AdapterAdmin(ArrayList<Admin> adminlist, Activity activity){
        this.activity = activity;
        this.adminlist = adminlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admin, parent, false);
        AdapterAdmin.ViewHolder holder = new AdapterAdmin.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        viewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ViewModel.class);
        holder.tvUsuario.setText(adminlist.get(position).getUsuario());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(activity);
                builder.setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        viewModel.deleteAdmin(adminlist.get(position).getId());
                        viewModel.getAllAdmin();

                    }
                });

                builder.setMessage("¿Desea eliminar a este administrador?");

                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return adminlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsuario;
        ConstraintLayout parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUsuario = itemView.findViewById(R.id.tvusuarioadmin);
            parent = itemView.findViewById(R.id.parentrecycler);
        }
    }
}
