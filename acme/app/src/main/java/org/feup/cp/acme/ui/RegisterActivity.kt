package org.feup.cp.acme.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.feup.cp.acme.R
import org.feup.cp.acme.network.CustomerInfoResponse
import org.feup.cp.acme.network.HttpClient
import org.feup.cp.acme.network.HttpClientInterface
import org.feup.cp.acme.network.RegisterData
import org.feup.cp.acme.room.AppDatabase
import org.feup.cp.acme.room.User
import org.feup.cp.acme.room.entity.Customer
import org.feup.cp.acme.security.KeyStoreManager
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivity : AppCompatActivity() {

    /**
     * Register input variables
     */
    private var name: EditText? = null
    private var card: EditText? = null
    private var nif: EditText? = null
    private var nickname: EditText? = null
    private var password: EditText? = null

    /**
     * Creates the register activity and apply inputs listeners
     */
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

        addInputRegexValidation()
    }

    /**
     * Add OnFocusChangeListener to check if register input value matches regex
     */
    private fun addInputRegexValidation() {
        this.name!!.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus &&
                !Regex("^[a-zA-ZÀ-ú]+((['. -][a-zA-ZÀ-ú ])?[a-zA-ZÀ-ú ]*)*\$")
                    .containsMatchIn(this.name!!.text)
            )
                this.name!!.error = "Name is not valid."
        }

        this.card!!.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus &&
                !Regex("^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})\$")
                    .containsMatchIn(this.card!!.text)
            )
                this.card!!.error =
                    "Credit Card number is not valid.\nMust have 12 number without spaces."
        }

        this.nif!!.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus &&
                !Regex("([a-z]|[A-Z]|[0-9])[0-9]{7}([a-z]|[A-Z]|[0-9])")
                    .containsMatchIn(this.nif!!.text)
            )
                this.nif!!.error = "NIF is not valid.\nMust have 9 number without spaces."
        }

        this.nickname!!.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus &&
                !Regex("^(?=.{4,20}\$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])\$")
                    .containsMatchIn(this.nickname!!.text)
            )
                this.nickname!!.error =
                    "Nickname is not valid.\nMust be between 4 and 20 characters long, with no special characters."
        }

        this.password!!.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus &&
                !Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,}\$")
                    .containsMatchIn(this.password!!.text)
            )
                this.password!!.error =
                    "The password must contain at least 1 number and 1 letter and be at least 4 characters."
        }
    }

    /**.
     * Check if any register input is empty. Returns true if
     * it is, false otherwise.
     */
    private fun anyInputEmpty(): Boolean {
        return (TextUtils.isEmpty(this.name!!.text) || TextUtils.isEmpty(this.card!!.text)
                || TextUtils.isEmpty(this.nif!!.text) || TextUtils.isEmpty(this.nickname!!.text)
                || TextUtils.isEmpty(this.password!!.text))
    }

    /**
     * Check if any register input is invalid. Returns true if
     * it is, false otherwise.
     */
    private fun anyInvalidInput(): Boolean {
        return !(TextUtils.isEmpty(this.name!!.error) && TextUtils.isEmpty(this.card!!.error)
                && TextUtils.isEmpty(this.nif!!.error) && TextUtils.isEmpty(this.nickname!!.error)
                && TextUtils.isEmpty(this.password!!.error))
    }

    /**
     * Register listener
     */
    private fun btnRegisterAction(view: View) {
        // Validate input register fields before actually register the customer
//        if (anyInputEmpty() || anyInvalidInput()) {
//            return Toast.makeText(
//                applicationContext,
//                "All fields are required and must be valid!",
//                Toast.LENGTH_LONG
//            ).show()
//        }

        // Create customer data object
//        val customer = RegisterData(
//            this.name!!.text.toString(),
//            this.card!!.text.toString().toBigInteger(),
//            this.nif!!.text.toString().toInt(),
//            this.nickname!!.text.toString(),
//            this.password!!.text.toString()
//        )
        val customer = RegisterData("admin", 4231312312312311, 123456789, "admin", "a1234")

        // Check for key entry pair with customer current nickname
        if (!KeyStoreManager.isKeyEntryUnique(customer.nickname)) {
            return Toast.makeText(
                applicationContext,
                "Nickname chosen is already taken.",
                Toast.LENGTH_LONG
            ).show()
        }

        // Create new key pair for the current customer
        KeyStoreManager(customer.nickname).generateKeyPair()
        customer.certificate = KeyStoreManager.encodeCertToString(customer.nickname)

        // Register customer in servers' database
        val webService: HttpClientInterface = HttpClient.getInstance()!!.getEndpoint()

        webService.register(customer).enqueue(object : Callback<CustomerInfoResponse> {
            override fun onResponse(
                call: Call<CustomerInfoResponse>,
                response: Response<CustomerInfoResponse>
            ) {
                if (!response.isSuccessful) {
                    // Delete key pair entry from key store
                    KeyStoreManager.deleteKeyStoreEntry(customer.nickname)
                    Toast.makeText(
                        applicationContext,
                        JSONObject(response.errorBody()!!.string()).get("description").toString(),
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    val customerInfo = response.body()!!

                    // Store user information locally
                    AppDatabase.getInstance()!!.customerDao()
                        .insertAll(
                            Customer(
                                0,
                                customerInfo.uuid,
                                customerInfo.name,
                                customerInfo.card.toDouble(),
                                customerInfo.nif.toInt(),
                                customerInfo.nickname
                            )
                        )

                    // Create customer singleton instance
                    User.getInstance(customerInfo, customer.password.length)

                    val intent = Intent(this@RegisterActivity, HomeActivity::class.java)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<CustomerInfoResponse>, t: Throwable) {
                // Delete key pair entry from key store
                KeyStoreManager.deleteKeyStoreEntry(customer.nickname)
                println(t.stackTrace)
            }
        })

    }
}