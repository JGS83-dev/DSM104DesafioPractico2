package com.dsm104desafiopractico2.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dsm104desafiopractico2.R;
import com.dsm104desafiopractico2.clases.ListaProductos;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class HistorialAdapter extends RecyclerView.Adapter<HistorialAdapter.ViewHolder> {
    Context context;
    List<ListaProductos> listaItems;

    public HistorialAdapter(Context context, List listaItems){
        this.context = context;
        this.listaItems = listaItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.historial_adapter_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListaProductos item = listaItems.get(position);
        holder.txtNombre.setText(item.getNombre());
        holder.txtPrecio.setText("$" + String.valueOf(item.getPrecio()));
    }

    @Override
    public int getItemCount() {
        return listaItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtNombre;
        public TextView txtPrecio;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtNombre = (TextView) itemView.findViewById(R.id.txtNombre);
            txtPrecio = (TextView) itemView.findViewById(R.id.txtPrecio);
        }
    }
}
