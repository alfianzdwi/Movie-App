package com.dicoding.mymovie.utils

import androidx.sqlite.db.SimpleSQLiteQuery
import java.lang.StringBuilder

object SortUtils {
    const val MOVIE_ENTITIES = "movieentities"
    const val TV_SHOW_ENTITIES = "tvshowentities"
    const val NEWEST = "Newest"
    const val OLDEST = "Oldest"
    const val RANDOM = "Random"

    fun getSortedQuery(filter: String, table: String): SimpleSQLiteQuery{
        val simpleQuery = StringBuilder().append("SELECT * FROM $table ")
        if (filter == NEWEST) {
            simpleQuery.append("ORDER BY releaseDate DESC")
        } else if (filter == OLDEST) {
            simpleQuery.append("ORDER BY releaseDate ASC")
        } else if (filter == RANDOM) {
            simpleQuery.append("ORDER BY RANDOM()")
        }

        return  SimpleSQLiteQuery(simpleQuery.toString())
    }

}