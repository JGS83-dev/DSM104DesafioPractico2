package com.dsm104desafiopractico2.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dsm104desafiopractico2.adapters.CarritoAdapter
import com.dsm104desafiopractico2.adapters.HistorialAdapter
import com.dsm104desafiopractico2.adapters.ProductosAdapter
import com.dsm104desafiopractico2.clases.ListaProductos
import com.dsm104desafiopractico2.databinding.FragmentNotificationsBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        database = Firebase.database.reference
        var historial = ArrayList<ListaProductos>()
        database.child("Historial").get().addOnSuccessListener {
            it.children.forEach{
                it.children.forEach{
                    historial.add(ListaProductos(it.key,it.child("nombre").value.toString(),it.child("precio").value.toString().toDouble(),it.child("tipo").value.toString()))
                }
            }
            val recyclerViewHistorial: RecyclerView
            recyclerViewHistorial = binding.recyclerViewHistorial
            recyclerViewHistorial.layoutManager = LinearLayoutManager(context)

            val historialAdapter: HistorialAdapter = HistorialAdapter(context,historial);

            recyclerViewHistorial.adapter = historialAdapter
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