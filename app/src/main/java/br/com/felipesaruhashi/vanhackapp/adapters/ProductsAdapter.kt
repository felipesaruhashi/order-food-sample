package br.com.felipesaruhashi.vanhackapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import br.com.felipesaruhashi.vanhackapp.R
import br.com.felipesaruhashi.vanhackapp.VanhackApp
import br.com.felipesaruhashi.vanhackapp.models.Order
import br.com.felipesaruhashi.vanhackapp.models.OrderItem
import br.com.felipesaruhashi.vanhackapp.models.Product

class ProductsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var products:List<Product>? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        if ( holder is ViewHolder ) {

            val context = holder.itemView.context

            val product = products?.get(position)


            if ( product != null ) {
                holder.tvItemName?.text = product.name

                holder?.itemView.setOnClickListener {

                    if ( VanhackApp.order == null ) {
                        VanhackApp.order = Order()
                    }

                    val orderItem = VanhackApp
                            .order?.orderItems?.filter { it.productId == product.id }?.firstOrNull()

                    if ( orderItem != null ) {
                        orderItem.quantity =  orderItem.quantity as Int + 1
                    } else {
                        val item = OrderItem(product)
                        item.quantity = 1
                        item.price = product.price
                        (VanhackApp.order?.orderItems as ArrayList).add(item)
                    }
                    Toast.makeText(context, "You just added an element to the order!", Toast.LENGTH_SHORT).show()

                }
            }

        }
    }

    override fun getItemCount(): Int {
        return products?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val inflator = LayoutInflater.from(parent?.context)

        val view = inflator.inflate(R.layout.selector_item, parent, false)

        return ViewHolder(view)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        var tvItemName: TextView? = view.findViewById(R.id.tvItemName) as TextView

    }

}
