package org.feup.cp.acme.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.RequestBody
import org.feup.cp.acme.R
import org.feup.cp.acme.network.HttpClient
import org.feup.cp.acme.network.HttpClientInterface
import org.feup.cp.acme.network.RegisterData
import org.feup.cp.acme.network.RegisterResponse
import org.feup.cp.acme.security.KeyStoreManager
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        findViewById<View>(R.id.btn_register_action).setOnClickListener(this::btnRegisterAction)
    }

    private fun btnRegisterAction(view: View) {

        val webService: HttpClientInterface = HttpClient.getInstance()!!.getEndpoint()

        // TODO - Retrieve data from input fields and make sure it is not empty
        val nickname = "rmaria"

        // Check for key entry pair with customer current nickname
        if(!KeyStoreManager.isKeyEntryUnique(nickname)) {
            return println("Nickname chosen is already taken.") // TODO - Update UI with printed message
        }

        // Create customer data object
        val customer = RegisterData("Roberto Maria", 4231312312312311, 123456789, nickname, "a1234", null)

        // Create new key pair for the current customer
        KeyStoreManager(customer.nickname).generateKeyPair()
        customer.certificate = KeyStoreManager.encodeCertToString(nickname)

        // Register customer in servers' database
        webService.register(customer).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (!response.isSuccessful) {
                    // Delete key pair entry from key store
                    KeyStoreManager.deleteKeyStoreEntry(customer.nickname)

                    try {
                        return println(JSONObject(response.errorBody()!!.string()).get("description"))    // TODO - Update UI with printed message
                    } catch (e: Exception) {
                        println(e.stackTrace)
                    }
                } else {
                    // TODO - If everything is ok store the user information locally using room
                    println(response.body())
                    println("success")
//                    response.body()?.forEach {}
//
//                    val intent = Intent(this@RegisterActivity, HomeActivity::class.java)
//                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                println(t.stackTrace)
            }
        })

    }
}