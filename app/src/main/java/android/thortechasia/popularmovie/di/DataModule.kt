package android.thortechasia.popularmovie.di

import android.arch.persistence.room.Room
import android.thortechasia.popularmovie.data.lokal.AppDatabase
import android.thortechasia.popularmovie.repository.LokalPopularMovieDataSource
import android.thortechasia.popularmovie.repository.MovieRepository
import android.thortechasia.popularmovie.repository.RemotePopularMovieDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val dataModule = module {


    single { Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "movie-db")
        .build() }

    single { get<AppDatabase>().popularMovieDao() }


    single { LokalPopularMovieDataSource(get()) }

    single { RemotePopularMovieDataSource(get()) }

    single { MovieRepository(get(), get()) }

}