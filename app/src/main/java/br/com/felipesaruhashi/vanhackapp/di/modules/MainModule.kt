package br.com.felipesaruhashi.vanhackapp.di.modules

import br.com.felipesaruhashi.vanhackapp.api.auth.AuthApi
import br.com.felipesaruhashi.vanhackapp.api.auth.IAuthApi
import br.com.felipesaruhashi.vanhackapp.api.cousine.CousineApi
import br.com.felipesaruhashi.vanhackapp.api.cousine.ICousineApi
import br.com.felipesaruhashi.vanhackapp.api.product.IProductApi
import br.com.felipesaruhashi.vanhackapp.api.product.ProductApi
import br.com.felipesaruhashi.vanhackapp.api.retrofit.IApi
import br.com.felipesaruhashi.vanhackapp.api.retrofit.VanhackApi
import br.com.felipesaruhashi.vanhackapp.api.store.IStoreApi
import br.com.felipesaruhashi.vanhackapp.api.store.StoreApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class MainModule {

    @Singleton
    @Provides
    fun providesRetrofit(): IApi {
        return VanhackApi()
    }

    @Singleton
    @Provides
    fun providesAuth(): IAuthApi {
        return AuthApi()
    }

    @Singleton
    @Provides
    fun providesCousineModule() : ICousineApi {
        return CousineApi()
    }

    @Singleton
    @Provides
    fun providesProductApi() : IProductApi {
        return ProductApi()
    }


    @Singleton
    @Provides
    fun providesStoreApi() : IStoreApi {
        return StoreApi()
    }

}
