package org.feup.cp.acme.room

import org.feup.cp.acme.network.CustomerInfoResponse

class User(val currentUser: CustomerInfoResponse, val passwordLength: Int) {

    /**
     * Static functions
     */
    companion object {

        /**
         * Current user instance
         */
        var instance: User? = null

        /**
         * Returns current user instance
         */
        fun getInstance(currentUser: CustomerInfoResponse? = null, passwordLength: Int? = null): User? {
            if (instance == null){
                synchronized(lock = true) {
                    instance = User(currentUser!!, passwordLength!!)
                }
            }
            return instance
        }

        /**
         * Destroys database instance
         */
        fun destroyUser(){
            instance = null
        }
    }
}