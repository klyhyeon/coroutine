package com.kotlin.coroutine.lecture

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

class Lec02 {
}

// runBlocking: 일반 세계와 코루틴 세계를 연결한다. 이 함수 자체로 새로운 코루틴을 만든다!
// launch: 반환값이 없는 코루틴을 만든다.
fun main(): Unit = runBlocking {
    printWithThread("START")
    launch {
        newRoutine()
    }
    yield()
    printWithThread("END")
}

// suspend는 다른 suspend function을 호출할 수 있다.
suspend fun newRoutine() {
    val num1 = 1
    val num2 = 2
    // 지금 코루틴을 중단하고 다른 코루틴이 실행되도록 한다.(스레드를 양보한다.)
    yield()
    printWithThread("${num1 + num2}")
}

fun printWithThread(str: Any) {
    println("[${Thread.currentThread().name}] $str")
}
