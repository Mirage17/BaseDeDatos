package com.davidgrajales.basededatos.ui.actualizar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.davidgrajales.basededatos.R
import com.davidgrajales.basededatos.model.local.Deudor
import com.davidgrajales.basededatos.model.local.DeudorDAO
import com.davidgrajales.basededatos.model.remote.DeudorRemote
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_actualizar.*
import kotlinx.android.synthetic.main.fragment_create.et_telefono

class FragmentActualizar : Fragment() {

    var idDudorFirebase: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_actualizar, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideDeudorDatos()

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("deudores")
        bt_buscar.setOnClickListener {
            val nombre = et_nombre.text.toString()


            //buscarEnRoom(deudorDAO, nombre, idDeudor)

            buscarEnFirebase(nombre, myRef)
            habilitarWidgetsBuscar()

        }

        bt_actualizar.setOnClickListener {
            //actualizarEnRoom(idDeudor, deudorDAO)

            actualizarEnFirebase(myRef)
            habilitarWidgetsBuscar()

        }

    }

    private fun actualizarEnFirebase(myRef: DatabaseReference) {


        val childUpdate = HashMap<String, Any>()
        childUpdate["nombre"] = et_nombre.text.toString()
        childUpdate["telefono"] = et_telefono.text.toString()
        childUpdate["cantidad"] = et_deuda.text.toString().toLong()

        myRef.child(idDudorFirebase!!).updateChildren(childUpdate)

    }


    private fun habilitarWidgetsBuscar() {
        et_telefono.visibility = View.GONE
        et_deuda.visibility = View.GONE
        bt_buscar.visibility = View.VISIBLE
        bt_actualizar.visibility = View.GONE
    }

    private fun buscarEnFirebase(
        nombre: String,
        myRef: DatabaseReference
    ) {

        var deudorExiste = false
        val postListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {

                for (datasnapshot: DataSnapshot in snapshot.children) {
                    val deudor = datasnapshot.getValue(DeudorRemote::class.java)
                    if (deudor?.nombre == nombre) {
                        deudorExiste = true
                        et_telefono.setText(deudor.telefono)
                        et_deuda.setText(deudor.cantidad.toString())
                        idDudorFirebase = deudor.id

                    }

                }
                if (!deudorExiste) {
                    Toast.makeText(requireContext(), "Deudor no exte", Toast.LENGTH_LONG).show()
                }

            }

        }

        myRef.addListenerForSingleValueEvent(postListener)

    }

    private fun buscarEnRoom(
        deudorDAO: DeudorDAO,
        nombre: String,
        idDeudor: Int
    ) {
        var idDeudor1 = idDeudor
        val deudor = deudorDAO.buscarDeudor(nombre)

        if (deudor != null) {
            idDeudor1 = deudor.id
            hablitarWidgetsParaActuallizar()
            et_telefono.setText(deudor.phone.toString())
            et_deuda.setText(deudor.owe.toString())


        }
    }

    private fun hablitarWidgetsParaActuallizar() {
        et_telefono.visibility = View.VISIBLE
        et_deuda.visibility = View.VISIBLE
        bt_actualizar.visibility = View.VISIBLE
        bt_buscar.visibility = View.GONE
    }

    private fun hideDeudorDatos() {
        et_telefono.visibility = View.GONE
        et_deuda.visibility = View.GONE
        bt_actualizar.visibility = View.GONE
    }
}