package org.feup.cp.acme.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.feup.cp.acme.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        Drawer(this, "home")
    }
}