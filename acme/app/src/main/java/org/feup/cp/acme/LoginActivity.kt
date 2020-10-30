package org.feup.cp.acme

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        findViewById<View>(R.id.btn_register_link).setOnClickListener(this::btnRegisterLink)
        findViewById<View>(R.id.btn_login_action).setOnClickListener(this::btnLoginAction)
    }

    private fun btnLoginAction(view: View) {
        val intent = Intent(this, HomeActivity::class.java)

        startActivity(intent)
        TODO("Create login action")
    }

    private fun btnRegisterLink(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

}
