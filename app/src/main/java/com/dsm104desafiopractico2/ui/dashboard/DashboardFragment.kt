package com.dsm104desafiopractico2.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dsm104desafiopractico2.adapters.CarritoAdapter
import com.dsm104desafiopractico2.adapters.ProductosAdapter
import com.dsm104desafiopractico2.clases.ListaProductos
import com.dsm104desafiopractico2.databinding.FragmentDashboardBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var database: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        database = Firebase.database.reference
        var carrito = ArrayList<ListaProductos>()
        database.child("Carrito").get().addOnSuccessListener {
        it.children.forEach{
            carrito.add(ListaProductos(it.key,it.child("nombre").value.toString(),it.child("precio").value.toString().toDouble(),it.child("tipo").value.toString()))
        }
            val recyclerViewCarrito: RecyclerView
            recyclerViewCarrito = binding.recyclerViewCarrito
            recyclerViewCarrito.layoutManager = LinearLayoutManager(context)

            val carritoAdapter: CarritoAdapter = CarritoAdapter(context,carrito);

            recyclerViewCarrito.adapter = carritoAdapter
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