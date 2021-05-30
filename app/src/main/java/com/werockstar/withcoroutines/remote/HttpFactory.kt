package com.werockstar.withcoroutines.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object HttpFactory {

	private fun createOkHttp(): OkHttpClient {
		val logging = HttpLoggingInterceptor()
		logging.level = HttpLoggingInterceptor.Level.BASIC
		return OkHttpClient.Builder()
			.addInterceptor(logging)
			.build()
	}

	private fun createRetrofit(): Retrofit {
		return Retrofit.Builder()
			.client(createOkHttp())
			.baseUrl("https://api.github.com/")
			.addConverterFactory(MoshiConverterFactory.create())
			.build()
	}

	fun createAPI(): GithubAPI {
		val retrofit = createRetrofit()
		return retrofit.create(GithubAPI::class.java)
	}
}
