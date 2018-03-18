package br.com.felipesaruhashi.vanhackapp

import android.app.Application
import android.content.Context
import br.com.felipesaruhashi.vanhackapp.di.components.ApplicationComponent
import br.com.felipesaruhashi.vanhackapp.di.components.DaggerApplicationComponent
import br.com.felipesaruhashi.vanhackapp.models.Order
import br.com.felipesaruhashi.vanhackapp.models.OrderItem

class VanhackApp : Application() {

    companion object {
        lateinit var component:ApplicationComponent
        var token:String? = ""
        var mContext : Context? = null
        var order: Order? = null
    }


    override fun onCreate() {
        super.onCreate()


        component = DaggerApplicationComponent
                .builder()
                .build()


    }
}
