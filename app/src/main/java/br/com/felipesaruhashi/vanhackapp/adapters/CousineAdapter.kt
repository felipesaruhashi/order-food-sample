package br.com.felipesaruhashi.vanhackapp.adapters

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.felipesaruhashi.vanhackapp.R
import br.com.felipesaruhashi.vanhackapp.models.Cousine
import br.com.felipesaruhashi.vanhackapp.ui.store.StoresActivity


class CousineAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var cousines:List<Cousine>? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        if ( holder is ViewHolder ) {

            val context = holder.itemView.context


            val cousine = cousines?.get(position)


            if ( cousine != null ) {
                holder.tvItemName?.text = cousine.name

                holder?.itemView.setOnClickListener {

                    val cousineId = cousine.id

                    Log.i("info", "cousine id : ${cousineId}")
                    val i = Intent(context, StoresActivity::class.java)
                    i.putExtra(StoresActivity.STORE_PARAM, cousineId)
                    context.startActivity(i)
                }
            }

            cousines?.get(position).let {

            }
        }
    }

    override fun getItemCount(): Int {
        return cousines?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val inflator = LayoutInflater.from(parent?.context)

        val view = inflator.inflate(R.layout.selector_item, parent, false)

        return ViewHolder(view)
    }

    class ViewHolder(view:View): RecyclerView.ViewHolder(view) {

        var tvItemName:TextView? = view.findViewById(R.id.tvItemName) as TextView

    }

}
