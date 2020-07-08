package com.davidgrajales.basededatos.ui.actualizar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.davidgrajales.basededatos.R
import com.davidgrajales.basededatos.SesionRoom
import com.davidgrajales.basededatos.model.Deudor
import kotlinx.android.synthetic.main.fragment_actualizar.*
import kotlinx.android.synthetic.main.fragment_create.*
import kotlinx.android.synthetic.main.fragment_create.et_telefono

class FragmentActualizar : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_actualizar, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        et_telefono.visibility=View.GONE
        et_deuda.visibility=View.GONE
        bt_actualizar.visibility=View.GONE
        var idDeudor=0
        val deudorDAO=SesionRoom.database.DeudorDAO()
        bt_buscar.setOnClickListener {
            val nombre = et_nombre.text.toString()


            val deudor = deudorDAO.buscarDeudor(nombre)

            if (deudor != null) {
                idDeudor = deudor.id
                et_telefono.visibility = View.VISIBLE
                et_deuda.visibility = View.VISIBLE
                bt_actualizar.visibility = View.VISIBLE
                et_telefono.setText(deudor.phone.toString())
                et_deuda.setText(deudor.owe.toString())
                bt_buscar.visibility = View.GONE


            }
        }

            bt_actualizar.setOnClickListener {
                val deudor = Deudor(
                    idDeudor,
                    et_nombre.text.toString(),
                    et_telefono.text.toString(),
                    et_deuda.text.toString().toLong()
                )

            deudorDAO.actualizarDeuda(deudor)
            et_telefono.visibility=View.GONE
            et_deuda.visibility=View.GONE
            bt_buscar.visibility=View.VISIBLE
            bt_actualizar.visibility=View.GONE

        }

    }
}