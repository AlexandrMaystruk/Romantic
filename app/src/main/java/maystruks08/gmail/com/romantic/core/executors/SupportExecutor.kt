package maystruks08.gmail.com.romantic.core.executors

import maystruks08.gmail.com.domain.executor.ThreadExecutor
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SupportExecutor : ThreadExecutor() {

    override val mainExecutor: Scheduler
        get() = Schedulers.computation()

    override val postExecutor: Scheduler = AndroidSchedulers.mainThread()

    override val supportExecutor: Scheduler
        get() = Schedulers.io()

}