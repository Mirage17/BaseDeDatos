package com.davidgrajales.basededatos

import android.app.Application
import androidx.room.Room
import com.davidgrajales.basededatos.model.DeudorDataBase
import com.davidgrajales.basededatos.model_admin.AdminDatabase


class SesionRoom : Application() {
    companion object {
        lateinit var database: DeudorDataBase
        lateinit var database1: AdminDatabase
    }


    override fun onCreate() {
        super.onCreate()
        SesionRoom.database = Room.databaseBuilder(
            this,
            DeudorDataBase::class.java,
            "deudor_DB"
        ).allowMainThreadQueries()
            .build()

        SesionRoom.database1 = Room.databaseBuilder(
            this,
            AdminDatabase::class.java,
            "admin_db"
        ).allowMainThreadQueries().build()


    }
}

/*class AdminRoom:Application(){
    companion object{
        lateinit var database:AdminDatabase
    }

    override fun onCreate() {
        super.onCreate()

        AdminRoom.database= Room.databaseBuilder(
            this,
            AdminDatabase::class.java,
            "admin_db"
        ).allowMainThreadQueries().build()
    }

}*/