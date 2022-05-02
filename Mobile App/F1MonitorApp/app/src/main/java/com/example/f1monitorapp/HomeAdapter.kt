package com.example.f1monitorapp

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.myapplication.fragments.HomeFragment

class HomeAdapter(
    private val context: Context,
    fragmentManager: FragmentManager,
    private var totalTabs: Int) : FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            HomeFragment()
        } else {
            ProfileFragment()
        }
    }
}