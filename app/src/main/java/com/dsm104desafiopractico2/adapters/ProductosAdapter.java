package com.dsm104desafiopractico2.adapters;

import static androidx.appcompat.graphics.drawable.DrawableContainerCompat.Api21Impl.getResources;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dsm104desafiopractico2.R;
import com.dsm104desafiopractico2.clases.ListaProductos;

import org.w3c.dom.Text;

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
    }

    @Override
    public int getItemCount() {
        return listaItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtNombre;
        public TextView txtPrecio;
        public ImageView imagen;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtNombre = (TextView) itemView.findViewById(R.id.txtNombre);
            txtPrecio = (TextView) itemView.findViewById(R.id.txtPrecio);
            imagen = (ImageView) itemView.findViewById(R.id.imagenProducto);
        }
    }
}
