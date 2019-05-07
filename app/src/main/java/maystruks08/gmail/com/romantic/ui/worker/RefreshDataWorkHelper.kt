package maystruks08.gmail.com.romantic.ui.worker

import androidx.work.*
import maystruks08.gmail.com.domain.scheduler.SyncDataScheduler
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RefreshDataWorkHelper @Inject constructor(): SyncDataScheduler {

    override fun scheduleCashSync(period: Long, timeUnit: TimeUnit) {
        val refreshCacheWork = PeriodicWorkRequestBuilder<RefreshCacheWorker>(period, timeUnit)
            .addTag(REFRESH_CASH_WORK)
            .build()
        WorkManager.getInstance().enqueueUniquePeriodicWork(
            REFRESH_CASH_PERIODIC,
            ExistingPeriodicWorkPolicy.REPLACE,
            refreshCacheWork
        )
    }

    companion object {

        private const val REFRESH_CASH_WORK = "refresh_cash_work"

        private const val REFRESH_CASH_PERIODIC = "refresh_cash_periodic"


    }

}