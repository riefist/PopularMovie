package android.thortechasia.popularmovie.repository

import android.thortechasia.popularmovie.data.PopularMovie
import android.thortechasia.popularmovie.data.lokal.PopularMovieDao
import android.thortechasia.popularmovie.data.lokal.PopularMovieEntity
import android.thortechasia.popularmovie.data.remote.PopularMovieModel
import android.thortechasia.popularmovie.network.ApiService
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

class RemotePopularMovieDataSource(
    val apiService: ApiService
)  {


    fun getPopularMovies(): Single<PopularMovieModel.Response> = apiService.getPopularMovies()
//            .map { response -> response.movies.map { PopularMovie.from(it) } }

}