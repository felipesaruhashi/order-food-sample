package br.com.felipesaruhashi.vanhackapp.di.components

import android.app.Activity
import br.com.felipesaruhashi.vanhackapp.MainActivity
import br.com.felipesaruhashi.vanhackapp.api.auth.AuthApi
import br.com.felipesaruhashi.vanhackapp.api.cousine.CousineApi
import br.com.felipesaruhashi.vanhackapp.api.order.OrderApi
import br.com.felipesaruhashi.vanhackapp.api.product.ProductApi
import br.com.felipesaruhashi.vanhackapp.di.modules.MainModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(MainModule::class))
interface ApplicationComponent {

    fun inject(login: Activity)
    fun inject(mainActivity: MainActivity)
    fun inject(authApi: AuthApi)
    fun inject(cousineApi: CousineApi)
    fun inject(orderApi: OrderApi)
    fun inject(productApi: ProductApi)

}
