package android.thortechasia.popularmovie.data.lokal

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = arrayOf(
    PopularMovieEntity::class
), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun popularMovieDao() : PopularMovieDao


}