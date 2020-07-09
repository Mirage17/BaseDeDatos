package com.davidgrajales.basededatos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.davidgrajales.basededatos.model_admin.AdminDAO
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(1200)
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        b_loggin.setOnClickListener {
            val email = et_email.text.toString()
            val password = et_password.text.toString()

            val adminDAO: AdminDAO = SesionRoom.database1.AdminDAO()
            val admin = adminDAO.buscarAdmin(email)

            if (password == admin.password) {
                Toast.makeText(this, "Administrador reconocido", Toast.LENGTH_LONG).show()

                startActivity(Intent(this, MainActivity::class.java))
            } else {
                Toast.makeText(this, "Autenticación fallida", Toast.LENGTH_LONG).show()

            }
        }

        b_registrarse.setOnClickListener {
            startActivity(Intent(this, Registro_Activity::class.java))

        }


    }
}