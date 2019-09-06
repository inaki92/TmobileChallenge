package com.inaki.tmobilecodechallenge.Network

import com.inaki.tmobilecodechallenge.Model.Repositories.RepoModel
import com.inaki.tmobilecodechallenge.Model.Users.RepoUserModel
import com.inaki.tmobilecodechallenge.Util.REPOS_USER_ENDPOINT
import com.inaki.tmobilecodechallenge.Util.SEARCH_REPO_ENDPOINT
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

    @GET(REPOS_USER_ENDPOINT)
    suspend fun getReposFromUser(@Path("user") user: String): Response<List<RepoModel>>

    @GET(SEARCH_REPO_ENDPOINT)
    suspend fun getRepoSearched(@Path("user") user: String,
                                @Path("repo") repoName: String): Response<RepoModel>
}