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
            startActivity((Intent(this, MainActivity::class.java)))
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)




        b_2register.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }

        b_login.setOnClickListener {

            val email = et_correo.text.toString()
            val password = et_contraseña.text.toString()

            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    this
                ) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information

                        val user = mAuth.currentUser

                    } else {
                        // If sign in fails, display a message to the user.


                        Toast.makeText(
                            this, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                    // ...
                }



            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}