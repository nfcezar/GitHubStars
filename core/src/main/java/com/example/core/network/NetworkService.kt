package com.example.core.network

import retrofit2.Retrofit

interface NetworkService {
    fun buildRetrofit(url: String): Retrofit
}