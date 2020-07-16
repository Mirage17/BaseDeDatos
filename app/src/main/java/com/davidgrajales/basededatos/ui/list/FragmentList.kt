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
import com.davidgrajales.basededatos.model.remote.DeudorRemote
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_list.*


class FragmentList : Fragment() {

    private val allDeudores: MutableList<DeudorRemote> = mutableListOf()
    private lateinit var deudoresAdapter: DeudoresRVAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_list, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cargarDeudores()
        rv_deudores.layoutManager = LinearLayoutManager(
            requireContext(), RecyclerView.VERTICAL,
            false
        )

        rv_deudores.setHasFixedSize(true)
        rv_deudores.adapter = deudoresAdapter

    }

    private fun cargarDeudores() {


        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("deudores")

        val postListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {

                for (datasnapshot: DataSnapshot in snapshot.children) {
                    val deudor = datasnapshot.getValue(DeudorRemote::class.java)
                    allDeudores.add(deudor!!)
                }
                deudoresAdapter.notifyDataSetChanged()

            }

        }

        myRef.addValueEventListener(postListener)
    }


}