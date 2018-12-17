package android.thortechasia.popularmovie.di

import android.thortechasia.popularmovie.utils.scheduler.AppSchedulerProvider
import android.thortechasia.popularmovie.utils.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import org.koin.dsl.module.module

val schedulerModule = module {

    factory { CompositeDisposable() }

    single<SchedulerProvider> { AppSchedulerProvider() }

}