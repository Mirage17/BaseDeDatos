package com.davidgrajales.basededatos

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.davidgrajales.basededatos.model_admin.Admin
import com.davidgrajales.basededatos.model_admin.AdminDAO
import kotlinx.android.synthetic.main.activity_registro_.*
import java.sql.Types.NULL

class Registro_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_)

        b_registrarse.setOnClickListener {

            val email = et_email.text.toString()
            val pass = et_password.text.toString()
            val pass2 = et_rpassword.text.toString()

            if (pass == pass2) {
                val admin = Admin(NULL, email, pass)

                val adminDAO: AdminDAO = SesionRoom.database1.AdminDAO()
                adminDAO.insertAdmin(admin)

                onBackPressed()

            } else {
                Toast.makeText(this, "Las contraseñas no coinsiden", Toast.LENGTH_LONG).show()
            }


        }
    }
}