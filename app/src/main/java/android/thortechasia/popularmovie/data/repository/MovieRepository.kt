package android.thortechasia.popularmovie.data.repository

import android.thortechasia.popularmovie.data.PopularMovieModel
import io.reactivex.Observable

class MovieRepository(val remoteDataSource: RemoteMovieDataSource) {

    fun getPopularMovies(): Observable<PopularMovieModel.Response> =
            remoteDataSource.getPopularMovie()
}