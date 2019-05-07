package maystruks08.gmail.com.domain.scheduler

import java.util.concurrent.TimeUnit

interface SyncDataScheduler {

    fun scheduleCashSync(period: Long, timeUnit: TimeUnit)

}