package br.com.felipesaruhashi.vanhackapp.ui.store

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast

import br.com.felipesaruhashi.vanhackapp.R
import br.com.felipesaruhashi.vanhackapp.adapters.StoreAdapter
import br.com.felipesaruhashi.vanhackapp.api.cousine.CousineApi
import br.com.felipesaruhashi.vanhackapp.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_stores.*
import rx.android.schedulers.AndroidSchedulers

class StoresActivity : BaseActivity() {

    companion object {
        val STORE_PARAM = "STORE_ID"
    }

    var adapter:StoreAdapter? = null

    var cousineApi = CousineApi()
    var cousineId:Int = -1

    var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stores)

        context = this


        cousineId = intent.getIntExtra(STORE_PARAM, -1)

        if ( cousineId == -1 ) {
            finish()
        }

        adapter = StoreAdapter()

        rvStores.itemAnimator = DefaultItemAnimator()
        rvStores.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        rvStores.adapter = adapter


        cousineApi.getCousinesStores(cousineId)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe (
        { stores ->
            adapter?.stores = stores
            adapter?.notifyDataSetChanged()
        },
        { error ->
            Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
        })



    }
}
