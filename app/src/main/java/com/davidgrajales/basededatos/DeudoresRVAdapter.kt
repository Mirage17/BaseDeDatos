package com.davidgrajales.basededatos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.davidgrajales.basededatos.model.remote.DeudorRemote
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_deudor.view.*

class DeudoresRVAdapter(
    var context: ArrayList<DeudorRemote>,
    var deudoresList: ArrayList<DeudorRemote>
) : RecyclerView.Adapter<DeudoresRVAdapter.DeudoresViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DeudoresViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.item_deudor, parent, false)
        return DeudoresViewHolder(itemView, context)
    }

    override fun getItemCount(): Int = deudoresList.size

    override fun onBindViewHolder(
        holder: DeudoresViewHolder,
        position: Int
    ) {
        val deudor: DeudorRemote = deudoresList[position]
        holder.bindDeudor(deudor)
    }

    class DeudoresViewHolder(
        itemView: View,
        context:Context
):RecyclerView.ViewHolder(itemView) {
        fun bindDeudor(deudor: DeudorRemote) {
            itemView.tv_nombredeudor.text = deudor.nombre
            itemView.tv_monto_deuda.text = deudor.cantidad.toString()
            if (!deudor.urlPhoto.isNullOrEmpty())
                Picasso.get().load(deudor.urlPhoto).into(itemView.im_deudorphoto)
        }
    }
}

