package com.werockstar.withcoroutines

import androidx.lifecycle.ViewModel
import com.werockstar.withcoroutines.remote.GithubAPI
import com.werockstar.withcoroutines.remote.model.UserResponse
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import java.util.concurrent.TimeoutException

class MainViewModel constructor(private val api: GithubAPI) : ViewModel() {

    fun fetchUser(user: String) = flow {
        val response = api.fetchUser(user)
        emit(response)
    }

    fun zip() = flow {
        val user1 = flowOf(api.fetchUser("WeRockstar"))
        val user2 = flowOf(api.fetchUser("WeRockstar"))
        val zip = user1.zip(user2) { u1, u2 ->
            u1
        }
        emitAll(zip)
    }

    fun combine() = flow {
        val user1 = flowOf(api.fetchUser("WeRockstar"))
        val user2 = flowOf(api.fetchUser("WeRockstar"))
        val flow = user1.combine(user2) { u1, u2 ->
            u1
        }
        emitAll(flow)
    }

    @FlowPreview
    fun flatMap() = flow {
        val user1 = flowOf(api.fetchUser("WeRockstar"))
        val user2 = flowOf(api.fetchUser("WeRockstar"))
        val flow = user1.flatMapConcat { user2 }
        emitAll(flow)
    }

    fun catch() = flow {
        val user1 = flowOf(api.fetchUser("WeRockstar"))
        val user2 = flowOf(api.fetchUser("WeRockstar"))
        val flow = user1.combine(user2) { u1, u2 ->
            u1
        }.catch { emit(UserResponse("", "", "", 1, "", "", "", "")) }
        emitAll(flow)
    }
    
    fun retry() = flow {
        val user1 = flowOf(api.fetchUser("WeRockstar"))
        val user2 = flowOf(api.fetchUser("WeRockstar"))
        val flow = user1.combine(user2) { u1, u2 ->
            u1
        }.retryWhen { cause, attempt -> cause is TimeoutException && attempt < 3 }
        emitAll(flow)
    }

}
