package br.com.felipesaruhashi.vanhackapp.api.auth

import okhttp3.ResponseBody
import rx.Observable

interface IAuthApi  {

    fun login(login:String, password:String) : Observable<String>

}
