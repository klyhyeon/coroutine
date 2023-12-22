package com.kotlin.coroutine.lecture

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main(): Unit = runBlocking {
//    apiCall1(object: Callback { // callback 지옥!!
//        apiCall2(object: Callback)
//    })

    val time = measureTimeMillis {
        val job1 = async(start = CoroutineStart.LAZY) { apiCall1() } // callback 방식이 아닌 동기방식으로 쓸 수 있다.
        val job2 =
            async(start = CoroutineStart.LAZY) { apiCall2() } // LAZY 옵션을 쓰면 끝날때까지 기다림. start()를 쓰면 같이 기다려서 로딩이 두 배 걸리지 않음

        job1.start()
        job2.start()
        printWithThread(job1.await() + job2.await())
    }
    printWithThread("소요 시간 : $time ms")
}

suspend fun apiCall1(): Int {
    delay(1_000)
    return 1
}

suspend fun apiCall2(): Int {
    delay(1_000)
    return 2
}

fun example5(): Unit = runBlocking {
    val job = async {
        3 + 5
    }
    val eight = job.await() // await: async의 결과를 가져오는 함수
    printWithThread(eight)
}

fun example4(): Unit = runBlocking {
    val job1 = launch {
        delay(1_000)
        printWithThread("Job 1")
    }
    job1.join() //코루틴이 완전히 끝날때까지 기다렸다가 다음 코루틴이 시작

    val job2 = launch {
        delay(1_000)
        printWithThread("Job 2")
    }
    // 같이 1.1초 정도 걸려 끝나게 됨
}

fun example3(): Unit = runBlocking {
    val job = launch {
        (1..5).forEach {
            printWithThread(it)
            delay(500)
        }
    }

    delay(1_000L)
    job.cancel()
}

// launch는 반환값을 갖지 않는 코루틴 빌더이지만 job 코루틴 자체를 반환함
fun example2(): Unit = runBlocking {
    val job = launch(start = CoroutineStart.LAZY) {
        printWithThread("Hello launch")
    }
    delay(1_000L)
    job.start()
}

fun example1() {// runBlocking은 코루틴 빌더, 모든 코루틴이 끝날 때까지 스레드를 블락한다. 스레드를 점유하기 때문에 테스트 코드나 전체 코드 블록에만 걸어야 한다.
    runBlocking {
        printWithThread("START")
        launch {
            delay(2_000L) // yield() - 아무것도 하지않고 다른 스레드로 양보
            printWithThread("LAUNCH_END")
        }
    }
    printWithThread("END")
}