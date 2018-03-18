package br.com.felipesaruhashi.vanhackapp

import android.app.Application
import android.content.Context
import br.com.felipesaruhashi.vanhackapp.di.components.ApplicationComponent
import br.com.felipesaruhashi.vanhackapp.di.components.DaggerApplicationComponent

class VanhackApp : Application() {

    companion object {
        lateinit var component:ApplicationComponent
        var token:String? = ""
        var mContext : Context? = null
    }


    override fun onCreate() {
        super.onCreate()


        component = DaggerApplicationComponent
                .builder()
                .build()


    }
}
