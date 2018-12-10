package android.thortechasia.popularmovie.repository

import android.thortechasia.popularmovie.data.PopularMovie
import android.thortechasia.popularmovie.data.lokal.PopularMovieDao
import android.thortechasia.popularmovie.data.lokal.PopularMovieEntity
import android.thortechasia.popularmovie.data.remote.PopularMovieModel
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

class LokalPopularMovieDataSource(val popularMovieDao: PopularMovieDao){

    fun getPopularMovies(): Single<List<PopularMovie>> {
        return popularMovieDao.findAll().map { list ->
            list.map { PopularMovie.from(it) }
        }

    }

    fun addPopularMovies(movies: List<PopularMovieEntity>) {
        popularMovieDao.saveAll(movies)
    }

}