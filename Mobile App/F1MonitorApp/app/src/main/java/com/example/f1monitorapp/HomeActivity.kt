package com.example.f1monitorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.f1monitorapp.databinding.ActivityHomeBinding
import com.google.android.material.tabs.TabLayout

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        val tabLayout = binding.tabLayout

        tabLayout.addTab(binding.tabLayout.newTab().setText("Home"))
        tabLayout.addTab(binding.tabLayout.newTab().setText("Profile"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = HomeAdapter(this, supportFragmentManager, tabLayout.tabCount)
        binding.viewPager.adapter = adapter

        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        setContentView(binding.root)
    }
}