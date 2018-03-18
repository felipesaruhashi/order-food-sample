package br.com.felipesaruhashi.vanhackapp

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import br.com.felipesaruhashi.vanhackapp.adapters.CousineAdapter
import br.com.felipesaruhashi.vanhackapp.api.cousine.CousineApi
import br.com.felipesaruhashi.vanhackapp.ui.BaseActivity
import kotlinx.android.synthetic.main.content_main.*
import rx.android.schedulers.AndroidSchedulers

class MainActivity : BaseActivity() {


//    lateinit var cousineApi:CousineApi

    var cousineApi = CousineApi()

    var adapter : CousineAdapter? = null

    var mContext: Context? = null

    var loadingDialog: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
//        val toolbar = findViewById(R.id.toolbar) as Toolbar
//        setSupportActionBar(toolbar)


        VanhackApp.component.inject(this)

        mContext = this



        adapter = CousineAdapter()

        rvCousine.itemAnimator = DefaultItemAnimator()
        rvCousine.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        rvCousine.adapter = adapter

        loadingDialog = ProgressDialog.show(this, getString(R.string.loading),
                getString(R.string.loading_message), true)


        cousineApi.getCousines()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
        { cousines ->
            loadingDialog?.dismiss()
            adapter?.cousines = cousines
            adapter?.notifyDataSetChanged()
        },
        { error ->
            loadingDialog?.dismiss()
            Toast.makeText(mContext, error.message, Toast.LENGTH_SHORT).show()
        })

    }


}
