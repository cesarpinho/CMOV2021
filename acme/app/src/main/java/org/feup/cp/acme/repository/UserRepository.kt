package org.feup.cp.acme.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.feup.cp.acme.network.HttpClient
import org.feup.cp.acme.network.HttpClientInterface
import org.feup.cp.acme.network.Posts
import org.feup.cp.acme.room.dao.UserDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

class UserRepository {

    private val webService: HttpClientInterface = HttpClient.getInstance()!!.getEndpoint()

//    private val executor: Executor,
//    private val userDao: UserDao

    // Serves only as an example for project structure purposes
    fun getPosts() : LiveData<List<Posts>> {

        val data = MutableLiveData<List<Posts>>()

        webService.getPosts().enqueue(object : Callback<List<Posts>> {
            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                data.value = response.body()
//                response.body()?.forEach {}
            }

            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                println(t.stackTrace)
            }
        })
        return  data
    }

}