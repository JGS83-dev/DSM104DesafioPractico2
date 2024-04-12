package com.dsm104desafiopractico2.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dsm104desafiopractico2.adapters.ProductosAdapter
import com.dsm104desafiopractico2.clases.ListaProductos
import com.dsm104desafiopractico2.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val recyclerViewComida: RecyclerView
        val recyclerViewBebida: RecyclerView
        recyclerViewComida = binding.recyclerViewComidas
        recyclerViewBebida = binding.recyclerViewBebidas
//        recyclerViewComida.setHasFixedSize(true)
        recyclerViewComida.layoutManager = LinearLayoutManager(context)

//        recyclerViewBebida.setHasFixedSize(true);
        recyclerViewBebida.layoutManager = LinearLayoutManager(context)
        val productos:ArrayList<ListaProductos> = ArrayList()
        val producto = ListaProductos("Tacos",10.5)
        productos.add(producto);
        productos.add(producto);
        productos.add(producto);

        val comidasAdapter:ProductosAdapter = ProductosAdapter(context,productos);
        val bebidasAdapter:ProductosAdapter = ProductosAdapter(context,productos);

        recyclerViewComida.adapter = comidasAdapter
        recyclerViewBebida.adapter = bebidasAdapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}