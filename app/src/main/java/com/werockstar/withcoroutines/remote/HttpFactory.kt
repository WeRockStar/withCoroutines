package com.werockstar.withcoroutines.remote

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object HttpFactory {

	private fun createOkHttp(): OkHttpClient {
		return OkHttpClient.Builder()
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
