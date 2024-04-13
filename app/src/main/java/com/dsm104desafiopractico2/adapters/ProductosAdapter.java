package com.dsm104desafiopractico2.adapters;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dsm104desafiopractico2.R;
import com.dsm104desafiopractico2.clases.ListaProductos;
import com.dsm104desafiopractico2.ui.dashboard.DashboardFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ViewHolder> {
    Context context;
    List<ListaProductos> listaItems;

    public ProductosAdapter(Context context, List listaItems){
        this.context = context;
        this.listaItems = listaItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_producto,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListaProductos item = listaItems.get(position);
        holder.txtNombre.setText(item.getNombre());
        holder.txtPrecio.setText(String.valueOf(item.getPrecio()));
        holder.imagen.setImageResource(R.drawable._01_tacos_de_carnitas__carne_asada_y_al_pastor);
        holder.producto = item;
        holder.context = this.context;
    }

    @Override
    public int getItemCount() {
        return listaItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ListaProductos producto = new ListaProductos();
        public Context context;
        public TextView txtNombre;
        public TextView txtPrecio;
        public ImageView imagen;

        public Button btnAgregar;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtNombre = (TextView) itemView.findViewById(R.id.txtNombre);
            txtPrecio = (TextView) itemView.findViewById(R.id.txtPrecio);
            imagen = (ImageView) itemView.findViewById(R.id.imagenProducto);
            btnAgregar = (Button) itemView.findViewById(R.id.btnAgregar);

            btnAgregar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                    databaseReference.child("Carrito").child("actual").push().setValue(producto);
                    Toast.makeText(context, "Producto añadido exitosamente al carrito", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
