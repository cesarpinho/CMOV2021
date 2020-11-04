package org.feup.cp.acme.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.feup.cp.acme.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        Drawer(this, "home")
    }
}