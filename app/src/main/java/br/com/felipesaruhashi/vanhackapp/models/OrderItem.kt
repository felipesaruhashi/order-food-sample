package br.com.felipesaruhashi.vanhackapp.models

class OrderItem(product:Product) {


    var id:Int = 0
    var orderId:Int = 0
    var productId:Int? = 0

    @Transient var product: Product? = null
    var price:Float? = 0f
    var quantity:Int? = 0
    var total:Float? = 0f

    init {
        this.product = product
        this.productId = product.id

    }



}
