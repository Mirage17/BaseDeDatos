package com.davidgrajales.basededatos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()

    override fun onStart() {
        super.onStart()
        val user = mAuth.currentUser
        if (user != null) {
            go2MainActivity()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)




        b_2register.setOnClickListener {
            go2RegistroActivity()
        }

        b_login.setOnClickListener {

            val email = et_correo.text.toString()
            val password = et_contraseña.text.toString()

            signInWithFirebase(email, password)

            go2MainActivity()
        }

    }

    private fun signInWithFirebase(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    val user = mAuth.currentUser

                } else {


                    showMesagge("Autenticación fallida")

                }


            }
    }

    private fun showMesagge(mesage: String) {
        Toast.makeText(
            this, mesage,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun go2RegistroActivity() {
        startActivity(Intent(this, RegistroActivity::class.java))
    }

    private fun go2MainActivity() {
        startActivity((Intent(this, MainActivity::class.java)))
    }
}