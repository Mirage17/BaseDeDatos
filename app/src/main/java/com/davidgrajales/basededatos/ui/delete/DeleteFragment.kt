package com.davidgrajales.basededatos.ui.delete

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.davidgrajales.basededatos.R
import com.davidgrajales.basededatos.SesionRoom
import kotlinx.android.synthetic.main.fragment_delete.*


class DeleteFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt_delete.setOnClickListener{
            val nombre=et_nombre.text.toString()
            val deudorDAO=SesionRoom.database.DeudorDAO()
            val deudor=deudorDAO.buscarDeudor(nombre)

            if(deudor!=null){
                deudorDAO.borrarDeudor(deudor)
                et_nombre.setText("")
            }
            else
                Toast.makeText(context,"deudor no existe",Toast.LENGTH_LONG).show()
        }
    }


}