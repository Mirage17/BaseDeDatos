package com.davidgrajales.basededatos

import android.app.Application
import androidx.room.Room
import com.davidgrajales.basededatos.model.local.DeudorDataBase

class BaseDeDatos: Application() {

    companion object{
        lateinit var database: DeudorDataBase
    }

    override fun onCreate() {
        super.onCreate()

        database=Room.databaseBuilder(
            this, DeudorDataBase::class.java,
            "mis deudores_db"
        ).build()
    }
}