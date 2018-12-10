package android.thortechasia.popularmovie.data.lokal

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.thortechasia.popularmovie.data.remote.PopularMovieModel

@Entity (tableName = "movie")
data class PopularMovieEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo(name = "vote_average") val voteAverage: Double,
    val title: String,
    val poster_path: String,
    val overview: String,
    val release_date: String,
    val popularity: Double,
    val original_title: String
) {
    companion object {
        fun from(model: PopularMovieModel.Movie) = PopularMovieEntity(
            model.id,
            model.vote_average,
            model.title,
            model.poster_path,
            model.overview,
            model.release_date,
            model.popularity,
            model.original_title
        )
    }
}
