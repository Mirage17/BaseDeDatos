package com.davidgrajales.basededatos.model.local
import androidx.room.*

@Dao
interface DeudorDAO {

    @Insert
    fun insertDeudor(deudor: Deudor)

    @Query("SELECT * FROM tabla_deudor WHERE name LIKE :nombre")
    fun buscarDeudor(nombre: String): Deudor

    @Update
    fun actualizarDeuda(deudor: Deudor)

    @Delete
    fun borrarDeudor(deudor: Deudor)

    @Query("SELECT * FROM tabla_deudor")
    fun getDeudores():List<Deudor>
    
}