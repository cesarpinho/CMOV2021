package org.feup.cp.acme

import android.app.KeyguardManager
import android.content.Intent
import android.hardware.biometrics.BiometricManager
import android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG
import android.hardware.biometrics.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import android.os.Bundle
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import android.util.Base64.*
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.feup.cp.acme.network.HttpClient
import org.feup.cp.acme.network.Posts
import org.feup.cp.acme.security.KeyStoreManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.math.BigInteger
import java.security.*
import java.security.cert.Certificate
import java.util.*
import javax.security.auth.x500.X500Principal

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        findViewById<View>(R.id.btn_register_link).setOnClickListener(this::btnRegisterLink)
        findViewById<View>(R.id.btn_login_action).setOnClickListener(this::btnLoginAction)
    }

     private fun btnLoginAction(view: View) {

//        val callback = HttpClient.getInstance()!!.getEndpoint().getPosts()
//
//        callback.enqueue(object : Callback<List<Posts>> {
//            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
//
//                response.body()?.forEach {}
//
//                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
//                startActivity(intent)
//            }
//
//            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
//                println(t.stackTrace)
//            }
//        })

//         val intent = Intent(this, HomeActivity::class.java)
//
//         startActivity(intent)

    }

    private fun btnRegisterLink(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

}
