package com.github.akshaychordiya.kotlinx.coroutine.rx1

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import rx.Emitter
import rx.Observable
import rx.Observer

/**
 * Transforms given cold [Observable] into cold [Flow].
 *
 * The resulting flow is cold, which means that [Observable.subscribe] is called every time a terminal operator
 * is applied to the resulting flow.
 *
 * A channel with the [default][Channel.BUFFERED] buffer size is used. Use the [buffer] operator on the
 * resulting flow to specify a user-defined value and to control what happens when data is produced faster
 * than consumed, i.e. to control the back-pressure behavior.
 *
 * Refer [callbackFlow] documentation for more details on back-pressure handling.
 */
@ExperimentalCoroutinesApi
public fun <T : Any> Observable<T>.asFlow(): Flow<T> = callbackFlow {
    val observer = object : Observer<T> {
        override fun onNext(t: T) {
            offer(t)
        }

        override fun onError(e: Throwable) {
            close(e)
        }

        override fun onCompleted() {
            close()
        }
    }
    val subscription = subscribe(observer)
    awaitClose { subscription.unsubscribe() }
}


/**
 * Converts the given flow to a cold observable.
 * The original flow is cancelled when the observable subscriber is disposed.
 */
@ExperimentalCoroutinesApi
fun <T : Any> Flow<T>.asObservable(backpressureMode: Emitter.BackpressureMode = Emitter.BackpressureMode.NONE): Observable<T> {
    return Observable.create({ emitter ->
        /*
         * ATOMIC is used here to provide stable behaviour of subscribe+dispose pair even if
         * asObservable is already invoked from unconfined
         */
        val job = GlobalScope.launch(Dispatchers.Unconfined, start = CoroutineStart.ATOMIC) {
            try {
                collect { emitter.onNext(it) }
                emitter.onCompleted()
            } catch (e: Throwable) {
                // Ignore `CancellationException` as error, since it indicates "normal cancellation"
                if (e !is CancellationException) {
                    emitter.onError(e)
                } else {
                    emitter.onCompleted()
                }
            }
        }
        emitter.setCancellation { job.cancel() }
    }, backpressureMode)
}