package android.thortechasia.popularmovie.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.thortechasia.popularmovie.data.PopularMovieModel
import android.thortechasia.popularmovie.data.repository.MovieRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class MoviePresenter(
    private val repository: MovieRepository,
    private val compositeDisposable: CompositeDisposable
):ViewModel() {

//    private var mView: MainContract.View?=null
    private val movies: MutableLiveData<List<PopularMovieModel.Movies>>? = MutableLiveData()

    /*override fun onAttach(view: MainContract.View) {
        mView=view
    }

    override fun onDetach() {
        mView=null
    }*/

    fun getPopularMovie() {
        repository.getPopularMovies().subscribeBy(
            onNext = {
//                mView?.showPopularMovie(it.movies)
                movies?.postValue(it.movies)
                Timber.d("data successfully show")
            },
            onError = {
                Timber.e(it)
            }
        ).addTo(compositeDisposable = compositeDisposable)
    }

    fun getDataPopularMovie(): LiveData<List<PopularMovieModel.Movies>>? = movies

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}