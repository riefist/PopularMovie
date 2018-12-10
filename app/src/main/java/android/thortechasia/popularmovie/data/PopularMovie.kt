package android.thortechasia.popularmovie.data

import android.thortechasia.popularmovie.data.lokal.PopularMovieEntity
import android.thortechasia.popularmovie.data.remote.PopularMovieModel

data class PopularMovie(
    val id: Int,
    val title: String,
    val image: String,
    val vote: Double,
    val description: String
){
    companion object {
        fun from(data: PopularMovieEntity) = PopularMovie(
            data.id,
            data.title,
            data.poster_path,
            data.voteAverage,
            data.overview
        )

        fun from(data: PopularMovieModel.Movie) = PopularMovie(
            data.id,
            data.title,
            data.poster_path,
            data.vote_average,
            data.overview
        )
    }
}