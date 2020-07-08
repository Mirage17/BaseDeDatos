package com.davidgrajales.basededatos.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.davidgrajales.basededatos.model.Deudor
import com.davidgrajales.basededatos.model.DeudorDAO

@Database(entities = arrayOf(Deudor::class),version=1)
abstract class DeudorDataBase: RoomDatabase() {
    abstract  fun DeudorDAO(): DeudorDAO
}