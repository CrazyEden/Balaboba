package com.example.balaboba.dispatchers

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

@OptIn(InternalCoroutinesApi::class)
class TestCoroutineDispatcher : CoroutineDispatcher(), Delay {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        block.run()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun scheduleResumeAfterDelay(
        timeMillis: Long,
        continuation: CancellableContinuation<Unit>,
    ) {
        continuation.resume(Unit, null)
    }
}