package br.com.felipesaruhashi.vanhackapp.models

import java.util.*

class Order {

    var id:Int? = null
    var date: Date? = null
    var customerId:Int? = null
    var deliveryAddress:String? = null
    var contact: String? = null
    var storeId:Int? = null
    var orderItems: List<OrderItem>? = null

    var total:Float? = null
    var status:String? = null
    var lastUpdate:Date? = null



}
