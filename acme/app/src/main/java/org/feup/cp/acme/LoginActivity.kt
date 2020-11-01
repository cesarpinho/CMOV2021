package org.feup.cp.acme

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.feup.cp.acme.network.HttpClient
import org.feup.cp.acme.network.Posts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

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

         val intent = Intent(this, HomeActivity::class.java)

         startActivity(intent)

    }

    private fun btnRegisterLink(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

}
