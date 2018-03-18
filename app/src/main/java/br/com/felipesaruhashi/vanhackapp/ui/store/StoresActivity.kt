package br.com.felipesaruhashi.vanhackapp.ui.store

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast

import br.com.felipesaruhashi.vanhackapp.R
import br.com.felipesaruhashi.vanhackapp.VanhackApp
import br.com.felipesaruhashi.vanhackapp.adapters.StoreAdapter
import br.com.felipesaruhashi.vanhackapp.api.cousine.ICousineApi
import br.com.felipesaruhashi.vanhackapp.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_stores.*
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class StoresActivity : BaseActivity() {

    companion object {
        val STORE_PARAM = "STORE_ID"
    }

    var adapter:StoreAdapter? = null

    @Inject lateinit var cousineApi: ICousineApi

    var cousineId:Int = -1

    var context: Context? = null

    var loadingDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stores)

        context = this

        cousineId = intent.getIntExtra(STORE_PARAM, -1)


        VanhackApp.component.inject(this)

        adapter = StoreAdapter()

        rvStores.itemAnimator = DefaultItemAnimator()
        rvStores.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        rvStores.adapter = adapter

        if ( cousineId == -1 ) {
            finish()
        } else {
            loadingDialog = ProgressDialog.show(this, getString(R.string.loading),
                    getString(R.string.loading_message), true)

            cousineApi.getCousinesStores(cousineId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
            { stores ->
                loadingDialog?.dismiss()
                adapter?.stores = stores
                adapter?.notifyDataSetChanged()
            },
            { error ->
                loadingDialog?.dismiss()
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            })
        }



    }
}
