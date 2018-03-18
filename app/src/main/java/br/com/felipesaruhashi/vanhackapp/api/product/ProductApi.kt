package br.com.felipesaruhashi.vanhackapp.api.product

import br.com.felipesaruhashi.vanhackapp.VanhackApp
import br.com.felipesaruhashi.vanhackapp.api.retrofit.IApi
import br.com.felipesaruhashi.vanhackapp.api.retrofit.VanhackApi
import br.com.felipesaruhashi.vanhackapp.models.Product
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable
import javax.inject.Inject


class ProductApi : IProductApi {

    @Inject lateinit var api: IApi

    init {
        VanhackApp.component.inject(this)
    }

    override fun getProduct(): Observable<List<Product>> {
        return api.generateRetrofit()
        .flatMap {
            it.create(ProductService::class.java).getProduct()
        }
    }

    override fun searchProduct(searchText: String): Observable<List<Product>> {
        return api.generateRetrofit()
        .flatMap {
            it.create(ProductService::class.java).searchProduct(searchText)
        }
    }

    override fun getProductById(productId: Int): Observable<List<Product>> {
        return api.generateRetrofit()
        .flatMap {
            it.create(ProductService::class.java).getProductById(productId)
        }
    }

}

interface ProductService {

    @GET("api/v1/Product")
    fun getProduct(): Observable<List<Product>>


    @GET("api/v1/Product/search/{searchText}")
    fun searchProduct(@Path("searchText") searchText:String): Observable<List<Product>>


    @GET("api/v1/Product/{productId}")
    fun getProductById(@Path("productId") productId:Int) : Observable<List<Product>>

}
