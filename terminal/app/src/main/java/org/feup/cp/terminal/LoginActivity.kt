package org.feup.cp.terminal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {
    /**
     * Login input variables
     */
    private var nickname: EditText? = null
    private var password: EditText? = null

    /**
     * Creates the login activity and apply inputs listeners
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize input variables
        this.nickname = findViewById(R.id.input_login_nickname)
        this.password = findViewById(R.id.input_login_password)

        // Add login listener
        findViewById<Button>(R.id.btn_login_action).setOnClickListener(this::btnLoginAction)

        this.nickname!!.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val pattern = Regex("^(?=.{4,20}\$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])\$")
                if (!pattern.containsMatchIn(this.nickname!!.text))
                    this.nickname!!.error =
                        "Nickname is not valid.\nMust be between 4 and 20 characters long, with no special characters."
            }
        }
    }

    private fun btnLoginAction(view: View) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}