package com.example.core.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class NetworkServiceImpl : NetworkService {
    override fun buildRetrofit(url: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(url)
            .build()
    }
}