package com.dsm104desafiopractico2.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dsm104desafiopractico2.adapters.CarritoAdapter
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
        var total:Double = 0.0
        database.child("Carrito").get().addOnSuccessListener {
        it.child("actual").children.forEach{
            carrito.add(ListaProductos(it.key,it.child("nombre").value.toString(),it.child("precio").value.toString().toDouble(),it.child("tipo").value.toString()))
            total += it.child("precio").value.toString().toDouble()
        }
            val recyclerViewCarrito: RecyclerView
            recyclerViewCarrito = binding.recyclerViewCarrito
            recyclerViewCarrito.layoutManager = LinearLayoutManager(context)

            val carritoAdapter: CarritoAdapter = CarritoAdapter(context,carrito);
            binding.PrecioFinal.text = "$"+total.toString()

            var btnAgregar = binding.btnComprar;
            btnAgregar.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {

                    database.child("Carrito").child("actual").get().addOnSuccessListener {
                        var key = database.child("Historial").push().key.toString()
                        var productosCompra = ArrayList<ListaProductos>()
                        it.children.forEach{
                            //Agregamos a una lista
                             productosCompra.add(ListaProductos(it.key,it.child("nombre").value.toString(),it.child("precio").value.toString().toDouble(),it.child("tipo").value.toString()))
                        }
                        //Insertamos en la base de datos
                        database.child("Historial").child(key).setValue(productosCompra)
                        //Vaciamos el carrito
                        database.child("Carrito").child("actual").removeValue()
                    }.addOnFailureListener{
                        Log.e("firebase", "Error writing data", it)
                    }
                    Toast.makeText(
                        context,
                        "Comprada realizada con exito",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

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