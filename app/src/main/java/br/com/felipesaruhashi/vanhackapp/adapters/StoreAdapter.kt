package br.com.felipesaruhashi.vanhackapp.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.felipesaruhashi.vanhackapp.R
import br.com.felipesaruhashi.vanhackapp.VanhackApp
import br.com.felipesaruhashi.vanhackapp.models.Order
import br.com.felipesaruhashi.vanhackapp.models.Store
import br.com.felipesaruhashi.vanhackapp.ui.products.ProductsActivity
import br.com.felipesaruhashi.vanhackapp.ui.store.StoresActivity

class StoreAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var stores:List<Store>? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        if ( holder is ViewHolder ) {

            val context = holder.itemView.context


            val store = stores?.get(position)

            if ( store != null ) {
                holder.tvItemName?.text = store.name

                holder.itemView.setOnClickListener {

                    // reset the order
                    VanhackApp.order = Order()
                    VanhackApp.order?.storeId = store.id

                    val i = Intent(context, ProductsActivity::class.java)
                    i.putExtra(ProductsActivity.STORE_ID, store.id)
                    context.startActivity(i)
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return stores?.size ?: 0
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
