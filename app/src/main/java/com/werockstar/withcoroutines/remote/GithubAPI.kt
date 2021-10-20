package com.werockstar.withcoroutines.remote

import com.werockstar.withcoroutines.remote.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubAPI {
    @GET("users/{user}")
    suspend fun fetchUser(@Path("user") user: String): UserResponse
}
