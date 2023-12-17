package com.kotlin.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FistCoroutine {

    @Test
    fun firstWorkingCoroutine() {
        runBlocking {// this: CoroutineScope
            launch { //launch a new coroutine and continue
                delay(1000L) // non-blocking delay for 1 second
                println("World!") //print after delay
            }
            println("Hello") //main coroutine continues while a previous one is deplayed
        }
    }

    @Test
    fun main() = runBlocking {
        launch { doWorld() }
        println("Hello")
    }

    @Test
    suspend fun doWorld() {
        delay(1000L)
        println("World!")
    }

    @Test
    fun main2() = runBlocking {
        repeat(50_000) { // launch a lot of coroutines
            launch {
                delay(5000L)
                print(".")
            }
        }
    }
}