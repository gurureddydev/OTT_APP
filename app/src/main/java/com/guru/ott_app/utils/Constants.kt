package com.guru.ott_app.utils

import com.guru.ott_app.api.Response

object Constants {

    const val MENU_SEARCH = "search"
    const val MENU_HOME = "home"
    const val MENU_TV = "tv"
    const val MENU_MOVIE = "movie"
    const val MENU_SPORTS = "sports"
    const val MENU_LANGUAGE = "language"
    const val MENU_GENRES = "genres"
    const val MENU_SETTINGS = "settings"

     val SWIPE_THRESHOLD = 100
     val SEEK_FORWARD_INTERVAL = 10000 // 10 seconds
     val SEEK_BACKWARD_INTERVAL = 10000 // 10 seconds

    /* Intent tags*/
    const val INTENT_MENU = "menu"
    const val SELECTED_ID = "id"


    const val BASE_URL_IMG = "https://www.themoviedb.org/t/p/w780/"
    const val API_KEY = "461271c1ad63e406246afbba8351f2ac"

    fun isLoading(vararg responses: Response<*>): Boolean {
        return responses.any { it is Response.Loading }
    }
}