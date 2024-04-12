package com.dsm104desafiopractico2.ui.home

import android.os.Bundle
import android.util.Log
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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var database: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        database = Firebase.database.reference
        var comidas = ArrayList<ListaProductos>()
        var bebidas = ArrayList<ListaProductos>()
        database.child("Productos").get().addOnSuccessListener {
            it.children.forEach{
                if(it.child("tipo").value.toString() == "comida"){
                    comidas.add(ListaProductos(it.key,it.child("nombre").value.toString(),it.child("precio").value.toString().toDouble(),it.child("tipo").value.toString()))
                }else{
                    bebidas.add(ListaProductos(it.key,it.child("nombre").value.toString(),it.child("precio").value.toString().toDouble(),it.child("tipo").value.toString()))
                }
            }
            val recyclerViewComida: RecyclerView
            val recyclerViewBebida: RecyclerView
            recyclerViewComida = binding.recyclerViewComidas
            recyclerViewBebida = binding.recyclerViewBebidas
//        recyclerViewComida.setHasFixedSize(true)
            recyclerViewComida.layoutManager = LinearLayoutManager(context)

//        recyclerViewBebida.setHasFixedSize(true);
            recyclerViewBebida.layoutManager = LinearLayoutManager(context)

            val comidasAdapter:ProductosAdapter = ProductosAdapter(context,comidas);
            val bebidasAdapter:ProductosAdapter = ProductosAdapter(context,bebidas);

            recyclerViewComida.adapter = comidasAdapter
            recyclerViewBebida.adapter = bebidasAdapter
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}