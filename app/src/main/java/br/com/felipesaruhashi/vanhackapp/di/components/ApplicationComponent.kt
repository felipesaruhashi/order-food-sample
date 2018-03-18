package br.com.felipesaruhashi.vanhackapp.di.components

import br.com.felipesaruhashi.vanhackapp.MainActivity
import br.com.felipesaruhashi.vanhackapp.api.auth.AuthApi
import br.com.felipesaruhashi.vanhackapp.api.cousine.CousineApi
import br.com.felipesaruhashi.vanhackapp.api.order.OrderApi
import br.com.felipesaruhashi.vanhackapp.api.product.ProductApi
import br.com.felipesaruhashi.vanhackapp.di.modules.MainModule
import br.com.felipesaruhashi.vanhackapp.ui.checkout.CheckoutActivity
import br.com.felipesaruhashi.vanhackapp.ui.login.LoginActivity
import br.com.felipesaruhashi.vanhackapp.ui.products.ProductsActivity
import br.com.felipesaruhashi.vanhackapp.ui.store.StoresActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(MainModule::class))
interface ApplicationComponent {

    fun inject(loginActivity: LoginActivity)
    fun inject(mainActivity: MainActivity)
    fun inject(authApi: AuthApi)
    fun inject(cousineApi: CousineApi)
    fun inject(orderApi: OrderApi)
    fun inject(productApi: ProductApi)
    fun inject(productsActivity: ProductsActivity)
    fun inject(storesActivity: StoresActivity)
    fun inject(checkoutActivity: CheckoutActivity)

}
