package com.davidgrajales.basededatos.ui.create

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.davidgrajales.basededatos.R
import com.davidgrajales.basededatos.SesionRoom
import com.davidgrajales.basededatos.model.local.Deudor
import com.davidgrajales.basededatos.model.local.DeudorDAO
import com.davidgrajales.basededatos.model.remote.DeudorRemote
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_create.*
import java.io.ByteArrayOutputStream
import java.sql.Types.NULL

class CreateFragment : Fragment() {

    private val REQUEST_PICTURE_CAPTURE = 1234


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

        mostrarMensajeBienvenida()

        iv_picture.setOnClickListener {
            dispatchTakePictureListener()

        }


        bt_guardar.setOnClickListener {
            val nombre = et_nombre1.text.toString()
            val telefono = et_telefono.text.toString()
            val cantidad = et_cantidad.text.toString().toLong()


            guardarEnFirebase(nombre, telefono, cantidad)


            //guardarRoom(nombre, telefono, cantidad)

            et_nombre1.setText("")
            et_telefono.setText("")
            et_cantidad.setText("")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_PICTURE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            iv_picture.setImageBitmap(imageBitmap)
        }
    }

    private fun dispatchTakePictureListener() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictreIntent ->
            takePictreIntent.resolveActivity(requireActivity().packageManager)?.also {
                startActivityForResult(takePictreIntent, REQUEST_PICTURE_CAPTURE)
            }

        }
    }

    private fun mostrarMensajeBienvenida() {
        val mAuth = FirebaseAuth.getInstance()
        val user: FirebaseUser? = mAuth.currentUser
        val correo = user?.email

        Toast.makeText(activity?.applicationContext, "bienvenido $correo", Toast.LENGTH_LONG).show()
    }

    private fun guardarEnFirebase(nombre: String, telefono: String, cantidad: Long) {

        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference("deudores")
        val id = myRef.push().key
        var urlPhoto = ""

        val mStorage: FirebaseStorage = FirebaseStorage.getInstance()
        val photoRef = mStorage.reference.child(id!!)
        // Get the data from an ImageView as bytes
        iv_picture.isDrawingCacheEnabled = true
        iv_picture.buildDrawingCache()
        val bitmap = (iv_picture.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = photoRef.putBytes(data)
        val urlTask = uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            photoRef.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                urlPhoto = task.result.toString()

                val deudor = DeudorRemote(
                    id,
                    nombre,
                    telefono,
                    cantidad,
                    urlPhoto
                )
                myRef.child(id).setValue(deudor)

            } else {
                // Handle failures
                // ...
            }
        }

    }

    private fun guardarRoom(nombre: String, telefono: String, cantidad: Long) {
        val deudor = Deudor(
            NULL,
            nombre,
            telefono,
            cantidad

        )

        val deudorDAO: DeudorDAO = SesionRoom.database.DeudorDAO()
        deudorDAO.insertDeudor(deudor)
    }
}