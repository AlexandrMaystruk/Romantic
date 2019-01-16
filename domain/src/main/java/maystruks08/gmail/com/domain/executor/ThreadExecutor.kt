package maystruks08.gmail.com.domain.executor

import io.reactivex.Scheduler

abstract class ThreadExecutor {

    abstract val mainExecutor: Scheduler

    abstract val postExecutor: Scheduler

    abstract val supportExecutor: Scheduler

}