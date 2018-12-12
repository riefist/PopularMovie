package android.thortechasia.popularmovie.data.repository

import android.thortechasia.popularmovie.data.PopularMovie
import android.thortechasia.popularmovie.data.lokal.PopularMovieEntity
import io.reactivex.Single

class MovieRepository(
    val remoteMovieDataSource: RemoteMovieDataSource,
    val lokalMovieDataSource: LokalMovieDataSource
) : MovieDataSource {

    override fun addPopularMovies(movies: List<PopularMovieEntity>) {

    }

    override fun getPopularMovies(): Single<List<PopularMovie>> {
        return lokalMovieDataSource.getPopularMovies()
            .map { list ->
                list.map { PopularMovie.from(it) }
            }
            .flatMap {
                if (it.isEmpty()) getPopularFromRemote() else
                    Single.just(it)
            }
    }

    fun getPopularFromRemote(): Single<List<PopularMovie>> {
        return remoteMovieDataSource.getPopularMovies()
            .map { list ->
                list.movies.map { PopularMovie.from(it) }
            }
    }
}