package android.thortechasia.popularmovie.data.repository

import android.thortechasia.popularmovie.data.PopularMovieModel
import android.thortechasia.popularmovie.network.ApiService
import io.reactivex.Observable

class MovieRepository(val remoteMovieDataSource: RemoteMovieDataSource) {

    fun getPopularMovies() : Observable<PopularMovieModel.Response>
            = remoteMovieDataSource.getPopularMovies()

    companion object {
        var INSTANCE : MovieRepository? = null

        fun getInstance(remoteMovieDataSource: RemoteMovieDataSource) : MovieRepository =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: MovieRepository(remoteMovieDataSource).also { INSTANCE = it }
                }
    }

}