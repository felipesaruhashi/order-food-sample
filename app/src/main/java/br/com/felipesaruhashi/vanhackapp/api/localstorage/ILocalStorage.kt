package br.com.felipesaruhashi.vanhackapp.api.localstorage

import android.app.Activity

interface ILocalStorage {


    fun saveToken(activity: Activity?, token: String)
    fun getToken(activity: Activity): String

}
