package com.davidgrajales.basededatos.ui.delete

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.davidgrajales.basededatos.R
import com.davidgrajales.basededatos.SesionRoom
import com.davidgrajales.basededatos.model.remote.DeudorRemote
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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

        bt_delete.setOnClickListener {
            val nombre = et_nombre.text.toString()
            //borrarRoom(nombre)
            borrarFirebase(nombre)
        }
    }

    private fun borrarFirebase(nombre: String) {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("deudores")
        var deudorExiste = false
        val postListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {

                for (datasnapshot: DataSnapshot in snapshot.children) {
                    val deudor = datasnapshot.getValue(DeudorRemote::class.java)
                    if (deudor?.nombre == nombre) {
                        deudorExiste = true
                        myRef.child(deudor.id!!).removeValue()
                        val alertDialog: AlertDialog? = activity?.let {//cuadro de dialogo
                            val builder = AlertDialog.Builder(it)
                            builder.apply {
                                setMessage("Desea Eliminar el deudor $nombre ?")
                                setPositiveButton("Aceptar") { dialog, id ->
                                    myRef.child(deudor.id).removeValue()
                                }
                                setNegativeButton("Cancelar") { dialog, id ->

                                }
                            }
                            builder.create()

                        }

                        alertDialog?.show()
                    }

                }
                if (!deudorExiste) {
                    Toast.makeText(requireContext(), "Deudor no exte", Toast.LENGTH_LONG).show()
                }

            }


        }

        myRef.addValueEventListener(postListener)
    }

    private fun borrarRoom(nombre: String) {
        val deudorDAO = SesionRoom.database.DeudorDAO()
        val deudor = deudorDAO.buscarDeudor(nombre)

        if (deudor != null) {
            deudorDAO.borrarDeudor(deudor)
            et_nombre.setText("")
        } else
            Toast.makeText(context, "deudor no existe", Toast.LENGTH_LONG).show()
    }


}