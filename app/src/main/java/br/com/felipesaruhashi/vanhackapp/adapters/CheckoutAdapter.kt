package br.com.felipesaruhashi.vanhackapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.felipesaruhashi.vanhackapp.R
import br.com.felipesaruhashi.vanhackapp.models.Order

class CheckoutAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var order:Order? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        if ( holder is ViewHolder ) {

            val context = holder.itemView.context

            val orderItem = order?.orderItems?.get(position)


            if ( orderItem != null ) {
                holder.tvItemName?.text = orderItem.product?.name
                holder.tvAmount?.text = "X ${orderItem.quantity}"
            }
        }
    }

    override fun getItemCount(): Int {
        return order?.orderItems?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val inflator = LayoutInflater.from(parent?.context)

        val view = inflator.inflate(R.layout.checkout_item, parent, false)

        return ViewHolder(view)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        var tvItemName: TextView? = view.findViewById(R.id.tvItemName) as TextView
        var tvAmount: TextView? = view.findViewById(R.id.tvAmount) as TextView

    }

}
