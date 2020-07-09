package com.davidgrajales.basededatos.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.davidgrajales.basededatos.R
import com.davidgrajales.basededatos.model.local.Deudor
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_create.*
import java.sql.Types.NULL

class CreateFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_create, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mAuth = FirebaseAuth.getInstance()
        val user: FirebaseUser? = mAuth.currentUser
        val correo = user?.email

        Toast.makeText(activity?.applicationContext, "bienvenido $correo", Toast.LENGTH_LONG).show()

        val database: FirebaseDatabase = FirebaseDatabase.getInstance()


        bt_guardar.setOnClickListener {
            val nombre = et_nombre1.text.toString()
            val telefono = et_telefono.text.toString()
            val cantidad = et_cantidad.text.toString().toLong()

            val deudor = Deudor(
                NULL,
                nombre,
                telefono,
                cantidad
            )


            /*val deudorDAO:DeudorDAO=SesionRoom.database.DeudorDAO()
            deudorDAO.insertDeudor(deudor)*/

            et_nombre1.setText("")
            et_telefono.setText("")
            et_cantidad.setText("")
        }
    }
}