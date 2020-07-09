package com.davidgrajales.basededatos

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_registro.*


class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val mAuth = FirebaseAuth.getInstance()

        b_registro.setOnClickListener {
            val email = et_correo.text.toString()
            val password = et_contraseña.text.toString()



            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    this
                ) { task ->
                    if (task.isSuccessful) {

                        crearUsuarioEnBaseDeDatos()
                        onBackPressed()


                    } else {

                        Toast.makeText(
                            this, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
        }

    }

    private fun crearUsuarioEnBaseDeDatos() {
        TODO("Not yet implemented")
    }
}