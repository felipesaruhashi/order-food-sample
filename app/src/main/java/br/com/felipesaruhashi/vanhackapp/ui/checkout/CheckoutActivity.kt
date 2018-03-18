package br.com.felipesaruhashi.vanhackapp.ui.checkout

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast

import br.com.felipesaruhashi.vanhackapp.R
import br.com.felipesaruhashi.vanhackapp.VanhackApp
import br.com.felipesaruhashi.vanhackapp.adapters.CheckoutAdapter
import br.com.felipesaruhashi.vanhackapp.api.order.OrderApi
import br.com.felipesaruhashi.vanhackapp.models.Order
import kotlinx.android.synthetic.main.activity_checkout.*
import rx.android.schedulers.AndroidSchedulers

class CheckoutActivity : AppCompatActivity() {

    var adapter: CheckoutAdapter? = null

    var orderApi = OrderApi()

    var context: Context? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        context = this

        adapter = CheckoutAdapter()

        adapter?.order = VanhackApp.order

        rvOrderItems.layoutManager = LinearLayoutManager(this)
        rvOrderItems.itemAnimator = DefaultItemAnimator()
        rvOrderItems.adapter = adapter


        val total = VanhackApp.order?.orderItems?.map { (it.price as Float) * (it.quantity as Int) }
                ?.sum()

        tvTotal.text = "$ ${String.format("%10.0f", total)}"

        VanhackApp.order?.total = total

        btnCheckout.setOnClickListener {

            if ( VanhackApp.order != null ) {

                orderApi.submitOrder(VanhackApp.order as Order)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Toast.makeText(context, "You ordered your request!", Toast.LENGTH_SHORT).show()
                    finish()
                },
                { error ->
                    Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                })
            }

        }

    }
}
