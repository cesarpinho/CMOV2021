package org.feup.cp.acme.room

import org.feup.cp.acme.network.CustomerInfoResponse

class User(val currentUser: CustomerInfoResponse) {

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
        fun getInstance(currentUser: CustomerInfoResponse? = null): User? {
            if (instance == null){
                synchronized(lock = true) {
                    instance = User(currentUser!!)
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