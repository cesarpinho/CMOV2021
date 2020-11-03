package org.feup.cp.acme.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import org.feup.cp.acme.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        findViewById<View>(R.id.btn_register_action).setOnClickListener(this::btnRegisterAction)
    }

    private fun btnRegisterAction(view: View) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)

//        TODO("Create Register action")

    }
}