package com.davidgrajales.basededatos.model_admin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tabla_admins")
class Admin(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String
)