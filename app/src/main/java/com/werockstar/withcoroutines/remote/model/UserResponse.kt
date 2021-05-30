package com.werockstar.withcoroutines.remote.model

import com.squareup.moshi.Json

data class UserResponse(
	@field:Json(name = "avatar_url")
	val avatarUrl: String,
	@field:Json(name = "bio")
	val bio: String,
	@field:Json(name = "company")
	val company: String,
	@field:Json(name = "id")
	val id: Int,
	@field:Json(name = "location")
	val location: String,
	@field:Json(name = "login")
	val username: String,
	@field:Json(name = "name")
	val name: String,
	@field:Json(name = "url")
	val url: String
)
