package com.werockstar.withcoroutines

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.werockstar.withcoroutines.remote.HttpFactory
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {

    private val job by lazy { MainScope() }

    private val viewModel by lazy { MainViewModel(HttpFactory.createAPI()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.fetchUser("WeRockStar")
            .onEach { }
            .launchIn(job)

        viewModel.zip()
            .onEach { }
            .launchIn(job)

        job.launch {
            val one = async(start = CoroutineStart.LAZY) { one() }
            val two = async(start = CoroutineStart.LAZY) { two() }
            one.start()

            Log.d("Result", "${one.await() + two.await()}")
        }

        job.launch {
            val one = async(start = CoroutineStart.LAZY) { one() }
            val two = async(start = CoroutineStart.LAZY) { two() }
            Log.d("Result", "${one.await() + two.await()}")
        }
    }

    private suspend fun one(): Int {
        delay(1000L) // pretend we are doing something useful here
        Log.d("Coroutines", "One")
        return 13
    }

    private suspend fun two(): Int {
        delay(1000L) // pretend we are doing something useful here, too
        Log.d("Coroutines", "Two")
        return 29
    }

    private suspend fun getString(): String = withContext(Dispatchers.IO) {
        "Hello"
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}
