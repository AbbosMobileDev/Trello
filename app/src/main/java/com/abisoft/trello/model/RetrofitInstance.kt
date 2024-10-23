package com.abisoft.trello.model

import com.abisoft.trello.model.api.AuthService
import com.abisoft.trello.model.api.TaskService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://rstask.wiremockapi.cloud/"

    private val retrofit by lazy {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val taskService: TaskService by lazy {
        retrofit.create(TaskService::class.java) // create() metodini bu yerda ishlatamiz
    }
}
