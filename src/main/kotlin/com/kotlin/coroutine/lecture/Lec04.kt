package com.kotlin.coroutine.lecture

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main(): Unit = runBlocking {
    val job = launch {
//        try {
            delay(100L)
//        } catch (e: CancellationException) {
//             do nothing
//        }

        printWithThread("delay에 의해 취소되지 않았다!")
    }
//    val job1 = launch(Dispatchers.Default) {
//        var i = 1
//        var nextPrintTime = System.currentTimeMillis()
//        while (i <= 5) {
//            if (nextPrintTime <= System.currentTimeMillis()) {
//                printWithThread("${i++}번째 출력!")
//                nextPrintTime += 1_000L
//            }
//            if (!isActive) {
//                throw CancellationException()
//            }
//        }
//    }


    delay(100L)
    job.cancel()
}