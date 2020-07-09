package com.davidgrajales.basededatos.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.davidgrajales.basededatos.R
import com.davidgrajales.basededatos.SesionRoom
import com.davidgrajales.basededatos.model.local.DeudorDAO
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_read.*

class ReadFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_read, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt_search.setOnClickListener{
            val nombre=et_nombre1.text.toString()

            val deudorDAO: DeudorDAO = SesionRoom.database.DeudorDAO()
            val deudor = deudorDAO.buscarDeudor(nombre)

            if (deudor!=null){
                tv_result.text="Nombre: ${deudor.name}\n Telefono: ${deudor.phone}\n"+
                        "Cantidad: ${deudor.owe}"
            }
            else{
                Toast.makeText(context,"Deudor no existe",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun burcarEnFirebase(nombre:String){
        val database =FirebaseDatabase.getInstance()
        val myref=database.getReference("deudores")


    }
}