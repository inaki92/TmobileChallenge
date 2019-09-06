package com.inaki.tmobilecodechallenge.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inaki.tmobilecodechallenge.Model.Repositories.RepoModel
import com.inaki.tmobilecodechallenge.Model.Users.RepoUserModel
import com.inaki.tmobilecodechallenge.Network.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReposViewModel(private val apiService: ApiService): ViewModel() {

    fun getUsersSearched(userName: String): MutableLiveData<RepoUserModel> {
        val users = MutableLiveData<RepoUserModel>()

        CoroutineScope(Dispatchers.Main).launch {
            val newUser: RepoUserModel = withContext(Dispatchers.IO) {
                apiService.getSearchedUser(userName).body()!!
            }
            users.postValue(newUser)
        }
        return users
    }

    fun getAllUsers(): MutableLiveData<List<RepoUserModel>> {
        val allUsers = MutableLiveData<List<RepoUserModel>>()

        CoroutineScope(Dispatchers.Main).launch {
            val allUser = withContext(Dispatchers.IO){
                apiService.getUsers().body()
            }
            allUsers.postValue(allUser)
        }
        return allUsers
    }

    fun getAllReposFromUser(user: String): MutableLiveData<List<RepoModel>> {
        val allRepos = MutableLiveData<List<RepoModel>>()

        CoroutineScope(Dispatchers.Main).launch {
            val allReposCall = withContext(Dispatchers.IO){
                apiService.getReposFromUser(user).body()
            }
            allRepos.postValue(allReposCall)
        }
        return allRepos
    }
}