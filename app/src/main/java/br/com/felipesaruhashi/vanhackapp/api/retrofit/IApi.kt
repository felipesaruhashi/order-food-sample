package br.com.felipesaruhashi.vanhackapp.api.retrofit

import retrofit2.Retrofit
import rx.Observable

interface IApi {
    fun generateRetrofit() : Observable<Retrofit>
}
