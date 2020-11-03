package org.feup.cp.acme.vmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import org.feup.cp.acme.network.RegisterData
import org.feup.cp.acme.repository.UserRepository

class UserViewModel(savedStateHandle: SavedStateHandle, userRepository: UserRepository): ViewModel() {

    val uuid : String = savedStateHandle["uuid"]?: throw IllegalArgumentException("Missing uuid")

    val user : LiveData<List<RegisterData>> = userRepository.getPosts()

}