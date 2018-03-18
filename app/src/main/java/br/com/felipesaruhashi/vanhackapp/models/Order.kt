package br.com.felipesaruhashi.vanhackapp.models

import java.util.*
import kotlin.collections.ArrayList

class Order {

    var id:Int? = null
    var date: Date? = null
    var customerId:Int? = 0
    var deliveryAddress:String? = "string"
    var contact: String? = "string"
    var storeId:Int? = 0
    var orderItems: List<OrderItem>? = null

    var total:Float? = 0f
    var status:String? = "WAITING"
    // CANCELED
    var lastUpdate:Date? = null


    init {
        this.date = Date()
        this.orderItems = ArrayList<OrderItem>() as List<OrderItem>?
    }



}
