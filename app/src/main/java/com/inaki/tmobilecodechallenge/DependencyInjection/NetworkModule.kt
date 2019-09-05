package com.inaki.tmobilecodechallenge.DependencyInjection

import com.inaki.tmobilecodechallenge.Util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun retrofitClient(): Retrofit =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()