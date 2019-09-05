package com.inaki.tmobilecodechallenge.Network

import com.inaki.tmobilecodechallenge.Model.RepoUserModel
import com.inaki.tmobilecodechallenge.Util.SEARCH_USER_ENDPOINT
import com.inaki.tmobilecodechallenge.Util.USERS_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(USERS_ENDPOINT)
    suspend fun getUsers(): Response<List<RepoUserModel>>

    @GET(SEARCH_USER_ENDPOINT)
    suspend fun getSearchedUser(@Path("user") user: String): Response<RepoUserModel>
}