package android.thortechasia.popularmovie.repository

import android.thortechasia.popularmovie.data.PopularMovie
import android.thortechasia.popularmovie.data.lokal.PopularMovieEntity
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single


interface PopularMovieDataSource {

    fun getPopularMovies() : Single<List<PopularMovie>>

}