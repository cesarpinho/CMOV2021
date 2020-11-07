package org.feup.cp.acme.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import org.feup.cp.acme.R
import org.feup.cp.acme.network.HttpClient
import org.feup.cp.acme.network.HttpClientInterface
import org.feup.cp.acme.network.RegisterData
import org.feup.cp.acme.network.CustomerInfoResponse
import org.feup.cp.acme.room.AppDatabase
import org.feup.cp.acme.room.User
import org.feup.cp.acme.room.entity.Customer
import org.feup.cp.acme.security.KeyStoreManager
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigInteger
import kotlin.math.absoluteValue


class RegisterActivity : AppCompatActivity() {

    /**
     * Register input variables
     */
    private var name: EditText? = null
    private var card: EditText? = null
    private var nif: EditText? = null
    private var nickname: EditText? = null
    private var password: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize input variables
        this.name = findViewById(R.id.input_register_name)
        this.card = findViewById(R.id.input_register_card)
        this.nif = findViewById(R.id.input_register_nif)
        this.nickname = findViewById(R.id.input_register_nickname)
        this.password = findViewById(R.id.input_register_password)

        // Add register button listener
        findViewById<View>(R.id.btn_register_action).setOnClickListener(this::btnRegisterAction)

        // TODO - Add OnFocusChangeListener to check if value matches regex
    }

    /**
     * Check if any register input is empty. Returns true if
     * it is, false otherwise.
     */
    private fun anyInputEmpty(): Boolean {
        return (TextUtils.isEmpty(name!!.text) || TextUtils.isEmpty(card!!.text)
                || TextUtils.isEmpty(nif!!.text) || TextUtils.isEmpty(nickname!!.text)
                || TextUtils.isEmpty(password!!.text))
    }

    /**
     * Check if any register input is invalid. Returns true if
     * it is, false otherwise.
     */
    private fun anyInvalidInput(): Boolean {
        return !(TextUtils.isEmpty(name!!.error) && TextUtils.isEmpty(card!!.error)
                && TextUtils.isEmpty(nif!!.error) && TextUtils.isEmpty(nickname!!.error)
                && TextUtils.isEmpty(password!!.error))
    }

    /**
     * Register listener
     */
    private fun btnRegisterAction(view: View) {
        // Validate input register fields before actually register
        if(anyInputEmpty() || anyInvalidInput()) {
            return println("All fields are required and must be valid!") // TODO - Update UI with printed message
        }

        // Create customer data object
        val customer = RegisterData(this.name!!.text.toString(),
                this.card!!.text.toString().toBigInteger(),
                this.nif!!.text.toString().toInt(),
                this.nickname!!.text.toString(),
                this.password!!.text.toString(),
                null)

        // Used for test only [To be removed]
//        val nickname = "rmaria"
//        val customer = RegisterData("Roberto Maria", 4231312312312311, 123456789, nickname, "a1234", null)

        // Check for key entry pair with customer current nickname
        if(!KeyStoreManager.isKeyEntryUnique(customer.nickname)) {
            return println("Nickname chosen is already taken.") // TODO - Update UI with printed message
        }

        // Create new key pair for the current customer
        KeyStoreManager(customer.nickname).generateKeyPair()
        customer.certificate = KeyStoreManager.encodeCertToString(customer.nickname)

        // Register customer in servers' database
        val webService: HttpClientInterface = HttpClient.getInstance()!!.getEndpoint()

        webService.register(customer).enqueue(object : Callback<CustomerInfoResponse> {
            override fun onResponse(call: Call<CustomerInfoResponse>, response: Response<CustomerInfoResponse>) {
                if (!response.isSuccessful) {
                    // Delete key pair entry from key store
                    KeyStoreManager.deleteKeyStoreEntry(customer.nickname)
                    println(JSONObject(response.errorBody()!!.string()).get("description")) // TODO - Update UI with printed message
                } else {
                    val customerInfo = response.body()!!

                    // Store user information locally
                    AppDatabase.getInstance()!!.customerDao()
                            .insertAll(Customer(0,
                                    customerInfo.uuid,
                                    customerInfo.name,
                                    customerInfo.card.toDouble(),
                                    customerInfo.nif.toInt(),
                                    customerInfo.nickname))

                    // Create customer singleton instance
                    User.getInstance(customerInfo)

                    val intent = Intent(this@RegisterActivity, HomeActivity::class.java)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<CustomerInfoResponse>, t: Throwable) {
                println(t.stackTrace)
            }
        })

    }
}