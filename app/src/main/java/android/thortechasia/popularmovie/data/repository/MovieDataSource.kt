package android.thortechasia.popularmovie.data.repository

import android.thortechasia.popularmovie.data.PopularMovieModel
import io.reactivex.Observable

interface MovieDataSource {
    fun getPopularMovie():Observable<PopularMovieModel.Response>
}