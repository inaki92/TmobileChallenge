package com.inaki.tmobilecodechallenge.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inaki.tmobilecodechallenge.Model.RepoUserModel
import com.inaki.tmobilecodechallenge.Network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReposViewModel(private val apiService: ApiService): ViewModel() {

    fun getUsersSearched(userName: String): MutableLiveData<RepoUserModel> {
        val users = MutableLiveData<RepoUserModel>()

        viewModelScope.launch {
            val newUser: RepoUserModel = withContext(Dispatchers.IO) {
                apiService.getSearchedUser(userName).body()!!
            }
            users.postValue(newUser)
        }
        return users
    }

    fun getAllUsers(): MutableLiveData<List<RepoUserModel>> {
        val allUsers = MutableLiveData<List<RepoUserModel>>()

        viewModelScope.launch {
            val allUser = withContext(Dispatchers.IO){
                apiService.getUsers().body()
            }
            allUsers.postValue(allUser)
        }
        return allUsers
    }
}