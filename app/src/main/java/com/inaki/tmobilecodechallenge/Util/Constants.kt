package com.inaki.tmobilecodechallenge.Util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

const val BASE_URL = "https://api.github.com/"

const val USERS_ENDPOINT = "users?since=2000"
const val SEARCH_USER_ENDPOINT = "users/{user}"
const val REPOS_USER_ENDPOINT = "users/{user}/repos"
const val SEARCH_REPO_ENDPOINT = "repos/{user}/{repo}"

@SuppressLint("SimpleDateFormat")
fun dateFormat(dateJoined: String): String {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
    return formatter.format(parser.parse(dateJoined))
}
