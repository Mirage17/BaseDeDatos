package com.davidgrajales.basededatos.model_admin

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Admin::class), version = 1)
abstract class AdminDatabase : RoomDatabase() {
    abstract fun AdminDAO(): AdminDAO
}