package com.davidgrajales.basededatos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.davidgrajales.basededatos.model.Deudor
import kotlinx.android.synthetic.main.item_deudor.view.*

class DeudoresRVAdapter (
    var context: Context,
    var deudoresList:ArrayList<Deudor>
) : RecyclerView.Adapter<DeudoresRVAdapter.DeudoresViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DeudoresViewHolder {
        var itemView=LayoutInflater.from(context).inflate(R.layout.item_deudor,parent,false)
        return DeudoresViewHolder(itemView,context)
    }

    override fun getItemCount(): Int = deudoresList.size

    override fun onBindViewHolder(
        holder: DeudoresViewHolder,
        position: Int
    ) {
        val deudor: Deudor = deudoresList[position]
        holder.binDeudor(deudor)
    }

    class DeudoresViewHolder(
        itemView: View,
        context: Context
    ) : RecyclerView.ViewHolder(itemView) {
        fun binDeudor(deudor: Deudor) {
            itemView.tv_nombre.text = deudor.name
            itemView.tv_deuda.text = deudor.owe.toString()
        }
    }
}

