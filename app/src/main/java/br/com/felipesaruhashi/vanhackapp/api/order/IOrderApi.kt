package br.com.felipesaruhashi.vanhackapp.api.order

import br.com.felipesaruhashi.vanhackapp.models.Order
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Path
import rx.Observable

interface IOrderApi {


    fun getOrder(@Path("orderId") orderId:Int): Observable<Order>

    fun submitOrder(@Body order:Order): Observable<ResponseBody>

    fun getCustomerOrder() : Observable<ResponseBody>
}
