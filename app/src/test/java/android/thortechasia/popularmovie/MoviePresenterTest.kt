package android.thortechasia.popularmovie

import android.thortechasia.popularmovie.data.PopularMovie
import android.thortechasia.popularmovie.data.repository.MovieRepository
import android.thortechasia.popularmovie.ui.movie.MovieContract
import android.thortechasia.popularmovie.ui.movie.MoviePresenter
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.never
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class MoviePresenterTest {

    private lateinit var presenter: MoviePresenter

    @Mock
    lateinit var repository: MovieRepository

    @Mock
    lateinit var view : MovieContract.View

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)

        presenter = MoviePresenter(repository, CompositeDisposable(), TestSchedulerProvider())
        presenter.onAttach(view)

    }

    @Test
    fun testGetPopularMovies(){
        //given
        val popularMovieList = listOf(mock(PopularMovie::class.java))
        given(repository.getPopularMovies()).willReturn(Single.just(popularMovieList))

        //when
        presenter.getPopularMovies()

        //then
        Mockito.verify(view).showLoading()
        Mockito.verify(repository).getPopularMovies()
        Mockito.verify(view).showPopularMovies(popularMovieList)
        Mockito.verify(view).hideLoading()
        Mockito.verify(view, never()).failureGetPopularMovies(MockitoKotlinHelper.any())
    }

    @Test
    fun testGetPopularMoviesFailed(){

        val throwable = Throwable("something wrong")
        given(repository.getPopularMovies()).willReturn(Single.error(throwable))

        presenter.getPopularMovies()

        Mockito.verify(view).showLoading()
        Mockito.verify(repository).getPopularMovies()
        Mockito.verify(view).hideLoading()
        Mockito.verify(view).failureGetPopularMovies(throwable)
        Mockito.verify(view, Mockito.never()).showPopularMovies(MockitoKotlinHelper.any())

    }
}