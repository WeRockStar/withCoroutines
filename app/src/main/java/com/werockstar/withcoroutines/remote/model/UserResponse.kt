package com.werockstar.withcoroutines.remote.model

import com.squareup.moshi.Json

data class UserResponse(
	@Json(name = "avatar_url")
	val avatarUrl: String,
	@Json(name = "bio")
	val bio: String,
	@Json(name = "company")
	val company: String,
	@Json(name = "id")
	val id: Int,
	@Json(name = "location")
	val location: String,
	@Json(name = "login")
	val username: String,
	@Json(name = "name")
	val name: String,
	@Json(name = "url")
	val url: String
)
