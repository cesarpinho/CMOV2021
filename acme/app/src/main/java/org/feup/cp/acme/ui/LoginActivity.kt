package org.feup.cp.acme.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import org.feup.cp.acme.R
import org.feup.cp.acme.room.AppDatabase

class LoginActivity : AppCompatActivity() {

    // private var db: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        findViewById<View>(R.id.btn_register_link).setOnClickListener(this::btnRegisterLink)
        findViewById<View>(R.id.btn_login_action).setOnClickListener(this::btnLoginAction)

        // db = AppDatabase.getAppDataBase(context = this)
    }

     private fun btnLoginAction(view: View) {

         val intent = Intent(this, HomeActivity::class.java)

         startActivity(intent)

    }

    private fun btnRegisterLink(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

}
