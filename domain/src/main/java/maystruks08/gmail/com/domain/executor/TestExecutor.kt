package maystruks08.gmail.com.domain.executor

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestExecutor : ThreadExecutor() {
    override val mainExecutor: Scheduler = Schedulers.trampoline()
    override val postExecutor: Scheduler = Schedulers.trampoline()
    override val supportExecutor: Scheduler = Schedulers.trampoline()
}