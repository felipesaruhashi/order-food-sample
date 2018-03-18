package br.com.felipesaruhashi.vanhackapp.ui.products

import android.app.ProgressDialog
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast

import br.com.felipesaruhashi.vanhackapp.R
import br.com.felipesaruhashi.vanhackapp.adapters.ProductsAdapter
import br.com.felipesaruhashi.vanhackapp.api.product.ProductApi
import br.com.felipesaruhashi.vanhackapp.ui.BaseActivity
import br.com.felipesaruhashi.vanhackapp.ui.store.StoresActivity
import kotlinx.android.synthetic.main.activity_products.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers

class ProductsActivity : BaseActivity() {


    companion object {
        val STORE_ID = "STORE_ID"
    }



    var adapter: ProductsAdapter? = null

    var productsApi = ProductApi()


    var storeId:Int? = null

    var context: Context? = null

    var loadingDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        this.context = this

        adapter = ProductsAdapter()


        rvProducts.itemAnimator = DefaultItemAnimator()
        rvProducts.layoutManager = LinearLayoutManager(this)
        rvProducts.adapter = adapter

        storeId = intent.getIntExtra(ProductsActivity.STORE_ID, -1)

        if ( storeId == -1 ) {
            finish()
        } else {
            loadingDialog = ProgressDialog.show(this, getString(R.string.loading),
                    getString(R.string.loading_message), true)

            productsApi.getProduct()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe (
            { products ->
                loadingDialog?.dismiss()
                adapter?.products = products.filter { it.storeId ==  storeId }
                adapter?.notifyDataSetChanged()

            },
            { error ->
                loadingDialog?.dismiss()
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT)
            })

        }

    }
}
