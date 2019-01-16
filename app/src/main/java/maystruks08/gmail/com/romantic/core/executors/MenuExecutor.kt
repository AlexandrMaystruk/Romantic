package maystruks08.gmail.com.romantic.core.executors

import android.os.HandlerThread
import maystruks08.gmail.com.domain.executor.ThreadExecutor
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MenuExecutor : ThreadExecutor() {

    override val mainExecutor: Scheduler = Schedulers.newThread()
    override val postExecutor: Scheduler = AndroidSchedulers.mainThread()
    override val supportExecutor: Scheduler
        get() {
            val newThread = HandlerThread(UI_WORKING_THREAD_NAME).apply { start() }
            return AndroidSchedulers.from(newThread.looper)
        }

    companion object {

        private const val UI_WORKING_THREAD_NAME = "UI:WorkingThread"

    }

}