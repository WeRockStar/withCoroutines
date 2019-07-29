package com.werockstar.withcoroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launch {
            val one = async(start = CoroutineStart.LAZY) { one() }
            val two = async(start = CoroutineStart.LAZY) { two() }
            one.start()

            Log.d("Result", "${one.await() + two.await()}")
        }

        launch {
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

}
