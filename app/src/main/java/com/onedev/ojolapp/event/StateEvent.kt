package com.onedev.ojolapp.event

sealed class StateEvent<T> {
    class Idle<T>: StateEvent<T>()
    class Loading<T>: StateEvent<T>()
    data class Failure<T>(val exception: Throwable): StateEvent<T>()
    data class Success<T>(val data: T): StateEvent<T>()
}
