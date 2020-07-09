package com.davidgrajales.basededatos.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="tabla_deudor")
class Deudor (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name="id") val id:Int,
    @ColumnInfo(name="name") val name: String,
    @ColumnInfo(name="phone") val phone:String,
    @ColumnInfo(name="cantidad") val owe:Long
)
