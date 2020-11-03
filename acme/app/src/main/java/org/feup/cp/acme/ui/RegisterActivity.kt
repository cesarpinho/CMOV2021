package org.feup.cp.acme.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.JsonReader
import android.view.View
import okhttp3.RequestBody
import org.feup.cp.acme.R
import org.feup.cp.acme.network.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        findViewById<View>(R.id.btn_register_action).setOnClickListener(this::btnRegisterAction)
    }

    private fun btnRegisterAction(view: View) {

        val webService: HttpClientInterface = HttpClient.getInstance()!!.getEndpoint()

        // TODO - Retrieve data from input fields and make sure it is validated
        // TODO - Generate certificate with alias being equal to the nickame. This way we easily verify if nickname was already used at least locally. Such verification must be made at the server level

        val user = RegisterData("Roberto Maria", 123456789, 123456789, "rmaria", "1234", "certificate")

        webService.register(user).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                // TODO - Check response code to see if everything was ok (possibility of non-unique identifier)
                // TODO - If everything is ok store the user information locally using room
                println(response.body())
//                response.body()?.forEach {}

//                val intent = Intent(this@RegisterActivity, HomeActivity::class.java)
//                startActivity(intent)
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                println(t.stackTrace)
            }
        })

    }
}