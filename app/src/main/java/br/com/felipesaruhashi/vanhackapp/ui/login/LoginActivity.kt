package br.com.felipesaruhashi.vanhackapp.ui.login

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.felipesaruhashi.vanhackapp.MainActivity
import br.com.felipesaruhashi.vanhackapp.R
import br.com.felipesaruhashi.vanhackapp.VanhackApp
import br.com.felipesaruhashi.vanhackapp.api.auth.IAuthApi
import br.com.felipesaruhashi.vanhackapp.api.localstorage.ILocalStorage
import br.com.felipesaruhashi.vanhackapp.api.localstorage.LocalStorage
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject lateinit var loginApi: IAuthApi

    @Inject lateinit var localStorage: ILocalStorage

    var mContext: Context? = null
    var loadingDialog:ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mContext = this

        VanhackApp.component.inject(this)

        val currentToken = localStorage.getToken(this)

        if ( currentToken.isNotEmpty() ) {
            VanhackApp.token = currentToken
            startActivity( Intent(mContext, MainActivity::class.java) )
        }

        btnLogin.setOnClickListener{

            if ( etLogin.text.isEmpty() || etPassword.text.isEmpty() ) {
                Toast.makeText(mContext, getString(R.string.missing_field), Toast.LENGTH_SHORT).show()
            } else {

                loadingDialog = ProgressDialog.show(this, getString(R.string.loading),
                        getString(R.string.loading_message), true)
                loginApi.login(etLogin.text.toString(), etPassword.text.toString())
                .subscribe({ token ->

                    localStorage.saveToken(mContext as Activity, token)

                    loadingDialog?.dismiss()

                    VanhackApp.token = token

                    val i = Intent(mContext, MainActivity::class.java)
                    mContext?.startActivity(i)

                }, { error ->
                    loadingDialog?.dismiss()
                    Toast.makeText(mContext, error.message, Toast.LENGTH_SHORT).show()
                })
            }
        }
    }
}
