package android.thortechasia.popularmovie.ui.movie

import android.thortechasia.popularmovie.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MoviePresenter(val movieRepository: MovieRepository,
                     val compositeDisposable: CompositeDisposable) : MovieContract.Presenter {

    private var mView: MovieContract.View? = null

    override fun onAttach(view: MovieContract.View) {
        mView = view
    }

    override fun onDetach() {
        mView = null
        compositeDisposable.clear()
    }

    override fun getPopularMovies() {
        mView?.showLoading()
        Timber.d("asdasd")
        movieRepository.getPopularMovies()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
            onSuccess = {
                Timber.d(it.size.toString())
                mView?.hideLoading()
                mView?.showPopularMovies(it)
            },
            onError = {
                mView?.hideLoading()
                Timber.e(it)
            }
        ).addTo(compositeDisposable)
    }
}