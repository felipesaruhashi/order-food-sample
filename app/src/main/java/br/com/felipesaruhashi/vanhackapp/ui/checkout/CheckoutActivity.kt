package br.com.felipesaruhashi.vanhackapp.ui.checkout

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import br.com.felipesaruhashi.vanhackapp.MainActivity

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
    var loadingDialog: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        context = this

        adapter = CheckoutAdapter()

        adapter?.order = VanhackApp.order

        rvOrderItems.layoutManager = LinearLayoutManager(this)
        rvOrderItems.itemAnimator = DefaultItemAnimator()
        rvOrderItems.adapter = adapter

        val total = VanhackApp
                .order
                ?.orderItems
                ?.map { (it.price as Float) * (it.quantity as Int) }
                ?.sum()

        tvTotal.text = "$ ${String.format("%10.0f", total)}"

        VanhackApp.order?.total = total

        btnCheckout.setOnClickListener {

            if ( VanhackApp.order != null ) {

                loadingDialog = ProgressDialog.show(this, getString(R.string.loading),
                        getString(R.string.loading_message), true)

                VanhackApp.order?.deliveryAddress = etCheckoutAddress.text.toString()
                VanhackApp.order?.contact = etContact.text.toString()

                orderApi.submitOrder(VanhackApp.order as Order)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    loadingDialog?.dismiss()
                    Toast.makeText(context, getString(R.string.you_ordered_your_request), Toast.LENGTH_SHORT).show()
                    finish()

                    val i = Intent(context, MainActivity::class.java)

                    i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP

                    context?.startActivity(i)

                },
                { error ->
                    loadingDialog?.dismiss()
                    Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                })
            }

        }

    }
}
