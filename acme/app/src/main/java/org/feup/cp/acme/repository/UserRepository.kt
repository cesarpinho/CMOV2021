package org.feup.cp.acme.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.feup.cp.acme.network.HttpClient
import org.feup.cp.acme.network.HttpClientInterface
import org.feup.cp.acme.network.RegisterData

class UserRepository {

    private val webService: HttpClientInterface = HttpClient.getInstance()!!.getEndpoint()

//    private val executor: Executor,
//    private val userDao: UserDao

    // Serves only as an example for project structure purposes
    fun getPosts() : LiveData<List<RegisterData>> {

        val data = MutableLiveData<List<RegisterData>>()

//        webService.getPosts().enqueue(object : Callback<List<Posts>> {
//            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
//                data.value = response.body()
//                response.body()?.forEach {}
//            }
//
//            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
//                println(t.stackTrace)
//            }
//        })
        return  data
    }

}