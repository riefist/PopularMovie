package android.thortechasia.popularmovie.repository

import android.thortechasia.popularmovie.data.PopularMovie
import android.thortechasia.popularmovie.data.lokal.PopularMovieEntity
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.nio.channels.SelectableChannel

class MovieRepository(
    val remoteMovieDataSource: RemotePopularMovieDataSource,
    val lokalPopularMovieDataSource: LokalPopularMovieDataSource
) : PopularMovieDataSource {


    override fun getPopularMovies(): Single<List<PopularMovie>> {
        return lokalPopularMovieDataSource.getPopularMovies()
            .flatMap {
                if (it.isEmpty()) getPopularMovieFromRemote() else
                    Single.just(it)
            }
            .doAfterSuccess {
                getPopularMovieFromRemote()
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        Timber.e("refresh data")
                    },{
                        Timber.e(it)
                    })
            }

    }

    private fun getPopularMovieFromRemote() : Single<List<PopularMovie>>{
        return remoteMovieDataSource.getPopularMovies()
            .doOnSuccess {
                lokalPopularMovieDataSource.
                    addPopularMovies(it.movies.map { data -> PopularMovieEntity.from(data) })
            }
            .map {
                it.movies.map { data -> PopularMovie.from(data) }
        }

    }


}


//    fun getPopularMovies(): Single<List<PopularMovie>> {
//        return lokalPopularMovieDataSource.getPopularMovies()
//    }
//
//    fun getPopularMovieFromRemote() : Maybe<List<PopularMovie>>{
//        return remoteMovieDataSource.getPopularMovies()
//    }

