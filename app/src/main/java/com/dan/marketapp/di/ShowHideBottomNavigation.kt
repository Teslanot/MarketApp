package com.dan.marketapp.di

import android.view.View
import androidx.fragment.app.Fragment
import com.dan.marketapp.R
import com.dan.marketapp.activities.ShoppingActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

fun Fragment.hideBottomNavigation() {
    val bottomNavigationView = (activity as ShoppingActivity).findViewById<BottomNavigationView>(com.dan.marketapp.R.id.bottomNavigation)
    bottomNavigationView.visibility = android.view.View.GONE
}
fun Fragment.showBottomNavigation() {
    val bottomNavigationView = (activity as ShoppingActivity).findViewById<BottomNavigationView>(com.dan.marketapp.R.id.bottomNavigation)
    bottomNavigationView.visibility = android.view.View.VISIBLE
}