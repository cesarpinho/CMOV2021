package org.feup.cp.acme.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import org.feup.cp.acme.R
import org.feup.cp.acme.network.*
import org.feup.cp.acme.room.AppDatabase
import org.feup.cp.acme.room.User
import org.feup.cp.acme.room.entity.Customer
import org.feup.cp.acme.security.KeyStoreManager
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    /**
     * Register input variables
     */
    private var nickname: EditText? = null
    private var password: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        AppDatabase.getInstance(context = this)

//        val inputNickname = findViewById<EditText>(R.id.input_login_nickname)
//        inputNickname.setOnFocusChangeListener { view, hasFocus ->
//            if (!hasFocus)
//                if ((view as EditText).text.length < 5)
//                    inputNickname.error = "Nick name is short"
//        }

        // Initialize input variables
        this.nickname = findViewById(R.id.input_login_nickname)
        this.password = findViewById(R.id.input_login_password)

        // Add buttons listeners
        findViewById<Button>(R.id.btn_register_link).setOnClickListener(this::btnRegisterLink)
        findViewById<Button>(R.id.btn_login_action).setOnClickListener(this::btnLoginAction)

        // TODO - Add OnFocusChangeListener to check if register input value matches regex
    }

    /**
     * Check if any login input is empty. Returns true if
     * it is, false otherwise.
     */
    private fun anyInputEmpty(): Boolean {
        return (TextUtils.isEmpty(this.nickname!!.text) || TextUtils.isEmpty(this.password!!.text))
    }

    /**
     * Check if the nickname is invalid.
     * Returns true if it is, false otherwise.
     */
    private fun anyInvalidInput(): Boolean {
        return !TextUtils.isEmpty(this.nickname!!.error)
    }

    private fun btnLoginAction(view: View) {
        // Validate input login fields before actually login the customer
        if(anyInputEmpty() || anyInvalidInput()) {
            return println("All fields are required and must be valid!") // TODO - Update UI with printed message
        }

        // Create customer data object
        val customer = LoginData(this.nickname!!.text.toString(), this.password!!.text.toString())
//        val customer = LoginData("rmaria", "a1234")

        // Create new key pair for the current customer if current nickname is absent on key store
        if(KeyStoreManager.isKeyEntryUnique(customer.nickname)) {
            KeyStoreManager(customer.nickname).generateKeyPair()
            customer.certificate = KeyStoreManager.encodeCertToString(customer.nickname)
        }

        // Login customer through server
        val webService: HttpClientInterface = HttpClient.getInstance()!!.getEndpoint()

        webService.login(customer).enqueue(object : Callback<CustomerInfoResponse> {
            override fun onResponse(call: Call<CustomerInfoResponse>, response: Response<CustomerInfoResponse>) {
                if (!response.isSuccessful) {
                    // Delete key pair entry from key store
                    KeyStoreManager.deleteKeyStoreEntry(customer.nickname)
                    println(JSONObject(response.errorBody()!!.string()).get("description")) // TODO - Update UI with printed message
                } else {
                    val customerInfo = response.body()!!

                    // Store user information locally if needed
                    if(AppDatabase.getInstance()!!.customerDao().getOne(customerInfo.nickname).isEmpty()) {
                        AppDatabase.getInstance()!!.customerDao()
                                .insertAll(Customer(0,
                                        customerInfo.uuid,
                                        customerInfo.name,
                                        customerInfo.card.toDouble(),
                                        customerInfo.nif.toInt(),
                                        customerInfo.nickname))
                    }

                    // Create customer singleton instance
                    User.getInstance(customerInfo)

                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<CustomerInfoResponse>, t: Throwable) {
                println(t.stackTrace)
            }
        })

    }

    private fun btnRegisterLink(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

}
