package br.com.felipesaruhashi.vanhackapp.ui.login

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.felipesaruhashi.vanhackapp.MainActivity
import br.com.felipesaruhashi.vanhackapp.R
import br.com.felipesaruhashi.vanhackapp.VanhackApp
import br.com.felipesaruhashi.vanhackapp.api.auth.AuthApi
import br.com.felipesaruhashi.vanhackapp.api.auth.IAuthApi
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

//    @Inject lateinit var loginApi: IAuthApi


    var loginApi = AuthApi()

    var mContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        mContext = this

        VanhackApp.component.inject(this)


        btnLogin.setOnClickListener{
            loginApi.login(etLogin.text.toString(), etPassword.text.toString())
            .subscribe({ token ->

                VanhackApp.token = token

                val i = Intent(mContext, MainActivity::class.java)
                mContext?.startActivity(i)

            }, { error ->
                Toast.makeText(mContext, error.message, Toast.LENGTH_SHORT).show()
            })
        }
    }
}
