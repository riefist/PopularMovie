package android.thortechasia.popularmovie.data.repository

import android.thortechasia.popularmovie.data.PopularMovie
import android.thortechasia.popularmovie.data.lokal.PopularMovieEntity
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MovieRepository(
    val remoteMovieDataSource: RemoteMovieDataSource,
    val lokalMovieDataSource: LokalMovieDataSource
) : MovieDataSource {


    override fun getDetailMovie(id: Int): Single<PopularMovie> {
        return lokalMovieDataSource.getDetailMovie(id)
            .map { PopularMovie.from(it) }
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
            .doAfterSuccess {
                getPopularFromRemote()
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        Timber.d("refresh data")
                    },{
                        Timber.e(it)
                    })
            }
    }

    private fun getPopularFromRemote(): Single<List<PopularMovie>> {
        return remoteMovieDataSource.getPopularMovies()
            .doOnSuccess {
                lokalMovieDataSource.savePopularMovies(it.movies.map { movie ->
                    PopularMovieEntity.from(movie)
                })
            }
            .map { list ->
                list.movies.map { PopularMovie.from(it) }
            }
    }
}