package com.example.practicalaravel.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.practicalaravel.R;
import com.example.practicalaravel.model.pojo.Consola;
import com.example.practicalaravel.viewmodel.ViewModel;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    ArrayList<Consola> consolaArrayList;
    Context context;
    View view;

    public RecyclerAdapter(ArrayList<Consola> consolaArrayList, Context context, View view){
        this.consolaArrayList = consolaArrayList;
        this.context = context;
        this.view = view;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlista_usuario, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvEstado.setText("Estado: "+consolaArrayList.get(position).getEstado());
        holder.tvNombre.setText("Consola: "+consolaArrayList.get(position).getNombre());
        holder.tvPrecio.setText("Precio: "+consolaArrayList.get(position).getPrecio()+"€");

        holder.tvFecha.setText("Fecha de compra: "+consolaArrayList.get(position).getFechaventa().toString());

        Glide.with(context)
                .load(consolaArrayList.get(position).getUrlpic())
                .error(R.drawable.er404)
                .into(holder.ivConsola);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            final NavController navController = Navigation.findNavController(view);

            @Override
            public void onClick(View v) {
                ViewModel.current =consolaArrayList.get(position);
                navController.navigate(R.id.action_listaFragment_to_editFragment);

            }
        });
    }

    @Override
    public int getItemCount() {
        return consolaArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvEstado, tvNombre, tvPrecio, tvFecha;
        ImageView ivConsola;
        ConstraintLayout parent;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEstado = itemView.findViewById(R.id.tvEstado);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            ivConsola = itemView.findViewById(R.id.ivConsola);
            parent = itemView.findViewById(R.id.constraintLista);
        }
    }
}
