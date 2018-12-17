package android.thortechasia.popularmovie.utils.scheduler

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun ui() : Scheduler
    fun io() : Scheduler
    fun computation() : Scheduler

}