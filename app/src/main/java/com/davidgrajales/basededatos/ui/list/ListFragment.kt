package com.davidgrajales.basededatos.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.davidgrajales.basededatos.DeudoresRVAdapter
import com.davidgrajales.basededatos.R
import com.davidgrajales.basededatos.SesionRoom
import com.davidgrajales.basededatos.model.Deudor
import com.davidgrajales.basededatos.model.DeudorDAO


class ListFragment : Fragment() {

    var allDeudores: List<Deudor> = emptyList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_list, container, false)

        val rv_deudores = root.findViewById<RecyclerView>(R.id.rv_deudores)

        rv_deudores.layoutManager = LinearLayoutManager(
            requireActivity().applicationContext,
            RecyclerView.VERTICAL,
            false
        )
        rv_deudores.setHasFixedSize(true)

        var deudorDao: DeudorDAO = SesionRoom.database.DeudorDAO()
        allDeudores = deudorDao.getDeudores()

        var deudoresRVAdapter = DeudoresRVAdapter(
            requireActivity().applicationContext,
            allDeudores as ArrayList<Deudor>
        )
        rv_deudores.adapter = deudoresRVAdapter
        deudoresRVAdapter.notifyDataSetChanged()

        // Inflate the layout for this fragment
        return root
    }

 
}