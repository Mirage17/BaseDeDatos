package com.davidgrajales.basededatos.model_admin

import androidx.room.*

@Dao
interface AdminDAO {
    @Insert
    fun insertAdmin(admin: Admin)

    @Query("SELECT * FROM tabla_admins WHERE email LIKE :e_mail")
    fun buscarAdmin(e_mail: String): Admin

    @Update
    fun actualizarAdmin(admin: Admin)

    @Delete
    fun borrarAdmin(admin: Admin)
}