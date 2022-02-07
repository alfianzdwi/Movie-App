package com.dicoding.mymovie.ui.home

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickBack
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn
import com.adevinta.android.barista.interaction.BaristaListInteractions.clickListItem
import com.dicoding.mymovie.R
import com.dicoding.mymovie.utils.DataDummy
import com.dicoding.mymovie.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummyTvShow = DataDummy.generateDummyTvShows()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)
    
    @Before
    fun setup() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovie() {
        assertDisplayed(R.id.rv_movie)
        clickListItem(R.id.rv_movie, dummyMovie.size)
    }

    @Test
    fun loadDetailMovie() {
        clickListItem(R.id.rv_movie, 0)
        assertDisplayed(R.id.image_poster)
        assertDisplayed(R.id.text_title)
        assertDisplayed(R.id.text_genre)
        assertDisplayed(R.id.text_release)
        assertDisplayed(R.id.text_description)
        assertDisplayed(R.id.text_duration)
        assertDisplayed(R.id.text_rating)
        assertDisplayed(R.id.text_language)
    }

    @Test
    fun loadTvShow() {
        clickOn("Tv Show")
        assertDisplayed(R.id.rv_tvShows)
        clickListItem(R.id.rv_tvShows, dummyTvShow.size)
    }

    @Test
    fun loadDetailTvShow() {
        clickOn("Tv Show")
        clickListItem(R.id.rv_tvShows, 0)
        assertDisplayed(R.id.image_poster)
        assertDisplayed(R.id.text_title)
        assertDisplayed(R.id.text_genre)
        assertDisplayed(R.id.text_release)
        assertDisplayed(R.id.text_description)
        assertDisplayed(R.id.text_duration)
        assertDisplayed(R.id.text_rating)
        assertDisplayed(R.id.text_language)

    }

    @Test
    fun loadFavoriteMovie() {
        clickListItem(R.id.rv_movie, 0)
        clickOn(R.id.action_favorite)
        clickBack()
        clickOn(R.id.favorite)
        clickListItem(R.id.rv_favorite_movie, 0)

        assertDisplayed(R.id.image_poster)
        assertDisplayed(R.id.text_title)
        assertDisplayed(R.id.text_genre)
        assertDisplayed(R.id.text_release)
        assertDisplayed(R.id.text_description)
        assertDisplayed(R.id.text_duration)
        assertDisplayed(R.id.text_rating)
        assertDisplayed(R.id.text_language)

        clickOn(R.id.action_favorite)
        clickBack()
    }


    @Test
    fun loadFavoriteTvShow() {
        clickOn("Tv Show")
        clickListItem(R.id.rv_tvShows,0)
        clickOn(R.id.action_favorite)
        clickBack()
        clickOn(R.id.favorite)
        clickOn("Tv Show")
        clickListItem(R.id.rv_favorite_tvShows, 0)

        assertDisplayed(R.id.image_poster)
        assertDisplayed(R.id.text_title)
        assertDisplayed(R.id.text_genre)
        assertDisplayed(R.id.text_release)
        assertDisplayed(R.id.text_description)
        assertDisplayed(R.id.text_duration)
        assertDisplayed(R.id.text_rating)
        assertDisplayed(R.id.text_language)

        clickOn(R.id.action_favorite)
        clickBack()
    }

}