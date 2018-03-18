package br.com.felipesaruhashi.vanhackapp.api.auth

import android.content.res.TypedArray
import br.com.felipesaruhashi.vanhackapp.VanhackApp
import br.com.felipesaruhashi.vanhackapp.api.retrofit.IApi
import br.com.felipesaruhashi.vanhackapp.api.retrofit.VanhackApi
import br.com.felipesaruhashi.vanhackapp.models.Customer
import okhttp3.ResponseBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable
import javax.inject.Inject


class AuthApi: IAuthApi {

    @Inject lateinit var api: IApi

    init {
        VanhackApp.component.inject(this)
    }


    override fun login(login: String, password: String) : Observable<String> {
        return api.generateRetrofit()
        .flatMap {
            it.create(LoginService::class.java).login(login, password)
        }.map {
            String(it.bytes())
        }

    }

}

interface LoginService {

    @FormUrlEncoded
    @POST("api/v1/Customer/auth")
    fun login(@Field("email") login:String, @Field("password") password: String): Observable<ResponseBody>


    @POST("api/v1/Customer")
    fun getCostumer(): Observable<Customer>
}
