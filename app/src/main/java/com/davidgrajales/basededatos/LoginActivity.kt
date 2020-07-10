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

            if (email.isEmpty() || password.isEmpty()) {
                showToast("Campos por llenar")

            } else {
                if (password == admin.password) {
                    showToast("Administrador reconocido")

                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    showToast("autenticación fallida")

                }

            }


        }

        b_registrarse.setOnClickListener {
            startActivity(Intent(this, Registro_Activity::class.java))

        }


    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}