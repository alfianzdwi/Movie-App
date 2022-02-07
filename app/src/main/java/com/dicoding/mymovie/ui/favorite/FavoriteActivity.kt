package com.dicoding.mymovie.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.mymovie.R
import com.dicoding.mymovie.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        val favoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(favoriteBinding.root)

        val sectionPagerFvAdapter = SectionPagerFvAdapter(this, supportFragmentManager)
        with(favoriteBinding){
            viewPager.adapter = sectionPagerFvAdapter
            tabs.setupWithViewPager(favoriteBinding.viewPager)

            supportActionBar?.elevation = 0f
        }
    }


}