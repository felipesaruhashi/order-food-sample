package br.com.felipesaruhashi.vanhackapp.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import br.com.felipesaruhashi.vanhackapp.R
import br.com.felipesaruhashi.vanhackapp.ui.checkout.CheckoutActivity

open class BaseActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if ( item?.itemId == R.id.action_checkout) {
            startActivity(Intent(this, CheckoutActivity::class.java))
        }

        return true
    }
}
