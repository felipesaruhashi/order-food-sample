package br.com.felipesaruhashi.vanhackapp.api.localstorage

import android.app.Activity
import android.content.Context

class LocalStorage: ILocalStorage {

    val TOKEN = "TOKEN"

    override fun saveToken(activity: Activity?, token: String) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(TOKEN, token)
            commit()
        }
    }

    override fun getToken(activity: Activity) : String{
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE) ?: return ""
        return sharedPref.getString(TOKEN, "")
    }

}
