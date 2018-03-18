package br.com.felipesaruhashi.vanhackapp

import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import br.com.felipesaruhashi.vanhackapp.adapters.CousineAdapter
import br.com.felipesaruhashi.vanhackapp.api.cousine.CousineApi
import kotlinx.android.synthetic.main.content_main.*
import rx.android.schedulers.AndroidSchedulers

class MainActivity : AppCompatActivity() {


//    lateinit var cousineApi:CousineApi

    var cousineApi = CousineApi()

    var adapter :CousineAdapter? = null

    var mContext: Context? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
//        val toolbar = findViewById(R.id.toolbar) as Toolbar
//        setSupportActionBar(toolbar)

        val fab = findViewById(R.id.fab) as FloatingActionButton


        VanhackApp.component.inject(this)

        mContext = this


        fab.setOnClickListener(View.OnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        })


        adapter = CousineAdapter()

        rvCousine.itemAnimator = DefaultItemAnimator()
        rvCousine.layoutManager = LinearLayoutManager(this)
        rvCousine.adapter = adapter


        cousineApi.getCousines()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
        { cousines ->
            adapter?.cousines = cousines
            adapter?.notifyDataSetChanged()
        },
        { error ->
            Toast.makeText(mContext, error.message, Toast.LENGTH_SHORT).show()
        })

    }

}
