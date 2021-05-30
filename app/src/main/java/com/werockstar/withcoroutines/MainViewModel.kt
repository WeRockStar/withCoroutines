package com.werockstar.withcoroutines

import androidx.lifecycle.ViewModel
import com.werockstar.withcoroutines.remote.GithubAPI
import kotlinx.coroutines.flow.flow

class MainViewModel constructor(private val api: GithubAPI) : ViewModel() {

	fun fetchUser(user: String) = flow {
		val response = api.fetchUser(user)
		emit(response)
	}

}
