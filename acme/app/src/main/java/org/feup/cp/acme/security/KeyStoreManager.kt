package org.feup.cp.acme.security

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64.*
import java.math.BigInteger
import java.security.*
import java.security.cert.Certificate
import java.util.*
import javax.security.auth.x500.X500Principal

class KeyStoreManager(alias: String) {

    /**
     * Keystore provider
     */
    private val provider = "AndroidKeyStore"

    /**
     * Key Pair Generator
     */
    private var kpg: KeyPairGenerator

    /**
     * Key Pair Generator Parameter Specification
     */
    private var parameterSpec: KeyGenParameterSpec

    /**
     * Key pair instance
     */
    private lateinit var kp: KeyPair

    /**
     * Key store instance
     */
    private var ks: KeyStore

    /**
     * Primary constructor
     */
    init {
        val startDate = GregorianCalendar()
        val endDate = GregorianCalendar()
        endDate.add(Calendar.YEAR, 1)

        // Creating a RSA key pair and store it in the Android Keystore
        this.kpg = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, this.provider)

        // Creating the key pair with sign and verify purposes
        this.parameterSpec = KeyGenParameterSpec
            .Builder(alias, KeyProperties.PURPOSE_SIGN or KeyProperties.PURPOSE_VERIFY)
            .run {
                setCertificateSerialNumber(BigInteger.valueOf(1))
                setCertificateSubject(X500Principal("CN=$alias"))
                setDigests(KeyProperties.DIGEST_SHA256)
                setSignaturePaddings(KeyProperties.SIGNATURE_PADDING_RSA_PKCS1)
                setCertificateNotBefore(startDate.time)
                setCertificateNotAfter(endDate.time)
                build()
            }

        // Initialization of key generator with the parameters we have specified above
        this.kpg.initialize(parameterSpec)

        // Retrieving the Keystore instance
        this.ks = KeyStore.getInstance("AndroidKeyStore")
            .apply {
                load(null)
            }
    }

    /**
     * Generates the key pair
     */
    fun generateKeyPair() {
        this.kp = this.kpg.genKeyPair()
    }

    /**
     * Static functions
     */
    companion object {

        /**
         * Deletes an existent entry in the key store instance
         */
        fun deleteKeyStoreEntry(alias: String) {
            val ks: KeyStore = KeyStore.getInstance("AndroidKeyStore")
                .apply {
                    load(null)
                }
            ks.deleteEntry(alias)
        }

        /**
         * Retrieves private key
         */
        fun getPrivateKey(alias: String): PrivateKey {
            val ks: KeyStore = KeyStore.getInstance("AndroidKeyStore")
                .apply {
                    load(null)
                }
            return ks.getKey(alias, null) as PrivateKey
        }

        /**
         * Retrieves certificate
         */
        fun getCertificate(alias: String): Certificate {
            val ks: KeyStore = KeyStore.getInstance("AndroidKeyStore")
                .apply {
                    load(null)
                }
            return ks.getCertificate(alias)
        }

        /**
         * Retrieves public key
         */
        fun getPublicKey(alias: String): PublicKey {
            return getCertificate(alias).publicKey
        }

        /**
         *  Signs data by means of private key
         */
        fun signData(data: String, privateKey: PrivateKey): String {
            val signature: ByteArray = Signature.getInstance("SHA256withRSA").run {
                initSign(privateKey)
                update(data.toByteArray())
                sign()
            }
            return encodeToString(signature, DEFAULT)
        }

        /**
         * Displays current entries present in the keystore
         */
        fun showKeyStoreEntries() {
            val ks: KeyStore = KeyStore.getInstance("AndroidKeyStore")
                .apply {
                    load(null)
                }
            println(ks.aliases().toList())
        }

        /**
         * Verifies certificate signature
         */
        fun verifySignature(signature: String, data: String, certificate: Certificate): Boolean {
            // Decode the signature value
            val decodedSignature: ByteArray = decode(signature, DEFAULT)

            // Check if the signature is valid. Use RSA algorithm along SHA-256 digest algorithm
            return Signature.getInstance("SHA256withRSA").run {
                initVerify(certificate)
                update(data.toByteArray())
                verify(decodedSignature)
            }
        }
    }
}