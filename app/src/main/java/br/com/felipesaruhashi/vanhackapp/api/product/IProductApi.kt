package br.com.felipesaruhashi.vanhackapp.api.product

import br.com.felipesaruhashi.vanhackapp.models.Product
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface IProductApi {

    fun getProduct(): Observable<List<Product>>

    fun searchProduct(@Path("searchText") searchText:String): Observable<List<Product>>

    fun getProductById(@Path("productId") productId:Int) : Observable<List<Product>>

}
