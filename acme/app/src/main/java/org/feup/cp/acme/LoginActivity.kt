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

//        val startDate = GregorianCalendar()
//        val endDate = GregorianCalendar()
//        endDate.add(Calendar.YEAR, 1)
//
//        val keyPairGenerator: KeyPairGenerator = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, "AndroidKeyStore")
//
//        val parameterSpec: KeyGenParameterSpec = KeyGenParameterSpec.Builder(
//                "test",
//                KeyProperties.PURPOSE_SIGN or KeyProperties.PURPOSE_VERIFY
//        ).run {
//            //setCertificateSerialNumber(BigInteger.valueOf(777))       //Serial number used for the self-signed certificate of the generated key pair, default is 1
//            setCertificateSubject(X500Principal("CN=test"))     //Subject used for the self-signed certificate of the generated key pair, default is CN=fake
//            setDigests(KeyProperties.DIGEST_SHA256)                         //Set of digests algorithms with which the key can be used
//            setSignaturePaddings(KeyProperties.SIGNATURE_PADDING_RSA_PKCS1) //Set of padding schemes with which the key can be used when signing/verifying
//            setCertificateNotBefore(startDate.time)                         //Start of the validity period for the self-signed certificate of the generated, default Jan 1 1970
//            setCertificateNotAfter(endDate.time)                            //End of the validity period for the self-signed certificate of the generated key, default Jan 1 2048
//            build()
//        }
//
//        //Initialization of key generator with the parameters we have specified above
//        keyPairGenerator.initialize(parameterSpec)
//
//        //Generates the key pair
//        var keyPair: KeyPair = keyPairGenerator.genKeyPair()
//
//        val keyStore: KeyStore = KeyStore.getInstance("AndroidKeyStore").apply {
//            load(null)
//        }

        //Retrieves the private key from the keystore
//        val privateKey: PrivateKey = keyStore.getKey("test", null) as PrivateKey

//        //We sign the data with the private key. We use RSA algorithm along SHA-256 digest algorithm
//        val signature: ByteArray? = Signature.getInstance("SHA256withRSA").run {
//            initSign(privateKey)
//            update("TestString".toByteArray())
//            sign()
//        }
//
//        var signatureResult: String? = ""
//
//        if (signature != null) {
//            //We encode and store in a variable the value of the signature
//            signatureResult = encodeToString(signature, DEFAULT)
//            println(signatureResult)
//        }
//
//        //We get the certificate from the keystore
//            val certificate: Certificate? = keyStore.getCertificate("test")
//
//        if (certificate != null) {
//            println(encodeToString(certificate.publicKey.encoded, DEFAULT))
//        }
//
//        if (certificate != null) {
//            //We decode the signature value
//            val signature2: ByteArray = decode(signatureResult, DEFAULT)
//
//            //We check if the signature is valid. We use RSA algorithm along SHA-256 digest algorithm
//            val isValid: Boolean = Signature.getInstance("SHA256withRSA").run {
//                initVerify(certificate)
//                update("TestString".toByteArray())
//                verify(signature2)
//            }
//            println(isValid)
//        }

//        val keyStore: KeyStore = KeyStore.getInstance("AndroidKeyStore").apply {
//            load(null)
//        }
//
//        val aliases: Enumeration<String> = keyStore.aliases()
//
//
//        println(aliases.toList())
//
//          keyStore.deleteEntry("test")


//        val ks: KeyStore = KeyStore.getInstance("AndroidKeyStore").apply {
//            load(null)
//        }
//        val aliases: Enumeration<String> = ks.aliases()
//
//        println(aliases.toList())
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
