package com.example.practicalaravel.view.adapter;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicalaravel.R;
import com.example.practicalaravel.model.pojo.Reparacion;
import com.example.practicalaravel.viewmodel.ViewModel;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ReparacionAdapter extends RecyclerView.Adapter<ReparacionAdapter.ViewHolder>{

    ArrayList<Reparacion> listaReparaciones;
    ViewModel viewModel;
    Dialog dialog;
    Context context;
    Activity activity;
    public ReparacionAdapter(ArrayList<Reparacion> listaReparaciones, Context context, Activity activity){
        this.listaReparaciones = listaReparaciones;
        this.context = context;
        this.activity = activity;
    }


    @NonNull
    @Override
    public ReparacionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reparacion, parent, false);
        ReparacionAdapter.ViewHolder holder = new ReparacionAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReparacionAdapter.ViewHolder holder, int position) {
        viewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ViewModel.class);
        holder.tvDescripcionReparacion.setText("Descripcion: "+ listaReparaciones.get(position).getDescripcion());
        holder.tvFechaReparacacion.setText("Fecha de Reparacion: "+ listaReparaciones.get(position).getFecha());
        holder.tvPrecioReparacion.setText("Precio: "+ listaReparaciones.get(position).getPrecio());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViewModel.currentRep = listaReparaciones.get(position);
                dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_editar_reparacion);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.CENTER);

                EditText etDescReparacionEdit = dialog.findViewById(R.id.etdescreparacionedit);
                EditText etPrecioReparacionEdit = dialog.findViewById(R.id.etprecioreparacionedit);
                EditText etFechaReparacionEdit = dialog.findViewById(R.id.etfechareparacionedit);
                TextView tvConsolaReparacionEdit = dialog.findViewById(R.id.tvconsolareparacionedit);
                Button btEditReparacion = dialog.findViewById(R.id.bteditreparacion);

                etDescReparacionEdit.setText(listaReparaciones.get(position).getDescripcion());
                etPrecioReparacionEdit.setText(listaReparaciones.get(position).getPrecio()+"");
                etFechaReparacionEdit.setText(listaReparaciones.get(position).getFecha());

                btEditReparacion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String desc = etDescReparacionEdit.getText().toString();
                        double precio = Double.parseDouble(etPrecioReparacionEdit.getText().toString());
                        String fecha = etFechaReparacionEdit.getText().toString();

                        Reparacion newReparacion = ViewModel.currentRep;
                        newReparacion.setDescripcion(desc);
                        newReparacion.setFecha(fecha);
                        newReparacion.setPrecio(precio);

                        viewModel.updateReparacion(newReparacion.getId(), newReparacion);
                        viewModel.getAllReparaciones();

                    }
                });

                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                window.setLayout(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaReparaciones.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout parent;
        TextView tvDescripcionReparacion, tvFechaReparacacion, tvPrecioReparacion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.constraintlayoutreparaciones);
            tvDescripcionReparacion = itemView.findViewById(R.id.tvdescripcionreparacion);
            tvFechaReparacacion = itemView.findViewById(R.id.tvfechareparacion);
            tvPrecioReparacion = itemView.findViewById(R.id.tvprecioreparacion);
        }
    }
}
