package br.com.felipesaruhashi.vanhackapp.api.order

import br.com.felipesaruhashi.vanhackapp.VanhackApp
import br.com.felipesaruhashi.vanhackapp.api.retrofit.IApi
import br.com.felipesaruhashi.vanhackapp.api.retrofit.VanhackApi
import br.com.felipesaruhashi.vanhackapp.models.Order
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import rx.Observable
import javax.inject.Inject

class OrderApi : IOrderApi {

//    @Inject lateinit var api: IApi
    var api = VanhackApi()

    init {
        VanhackApp.component.inject(this)
    }

    override fun getOrder(orderId: Int): Observable<Order> {
        return api.generateRetrofit().flatMap {
            it.create(OrderService::class.java)
                    .getOrder(orderId)
        }
    }

    override fun submitOrder(order: Order): Observable<ResponseBody> {

        val orderHash = HashMap<String, Any>()

        val items = ArrayList<HashMap<String, Any>>()

        order.orderItems?.forEach {
            val item = HashMap<String, Any>()
            item.put("productId", it.productId as Int)
            item.put("quantity", it.quantity as Int)

            items.add(item)
        }

        orderHash.put("deliveryAddress", order.deliveryAddress ?: "string")
        orderHash.put("contact", order.contact ?: "string")
        orderHash.put("storeId", order.storeId as Int)
        orderHash.put("orderItems", items)
        orderHash.put("status", "WAITING")

        return api.generateRetrofit().flatMap {
            it.create(OrderService::class.java)
                    .submitOrder(orderHash)
        }
    }

    override fun getCustomerOrder(): Observable<ResponseBody> {
        return api.generateRetrofit().flatMap {
            it.create(OrderService::class.java)
                    .getCustomerOrder()
        }
    }

}

interface OrderService {

    @GET("api/v1/Order/{orderId}")
    fun getOrder(@Path("orderId") orderId:Int): Observable<Order>


    @POST("api/v1/Order")
    fun submitOrder(@Body order:HashMap<String, Any>): Observable<ResponseBody>


    @GET("api/v1/Order/customer")
    fun getCustomerOrder() : Observable<ResponseBody>

}