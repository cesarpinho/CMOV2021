package org.feup.cp.acme.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.feup.cp.acme.R
import org.feup.cp.acme.room.User

class HomeActivity : AppCompatActivity() {

    /**
     * Creates the home activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        Toolbar(this, "home")
    }
}