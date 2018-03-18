package br.com.felipesaruhashi.vanhackapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.felipesaruhashi.vanhackapp.R
import br.com.felipesaruhashi.vanhackapp.models.Cousine


class CousineAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var cousines:List<Cousine>? = null


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

        if ( holder is ViewHolder ) {
            cousines?.get(position).let {
                holder.tvCousineName?.text = it?.name
            }

        }

    }

    override fun getItemCount(): Int {
        return cousines?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val inflator = LayoutInflater.from(parent?.context)

        val view = inflator.inflate(R.layout.cousine_item, parent, false)

        return ViewHolder(view)
    }

    class ViewHolder(view:View): RecyclerView.ViewHolder(view) {

        var tvCousineName:TextView? = view.findViewById(R.id.tvCousineName) as TextView

    }

}
