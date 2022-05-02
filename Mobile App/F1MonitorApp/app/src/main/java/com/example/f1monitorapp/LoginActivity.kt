package com.example.f1monitorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.f1monitorapp.databinding.ActivityLoginBinding
import com.google.android.material.tabs.TabLayout

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        val tabLayout = binding.tabLayout

        tabLayout.addTab(binding.tabLayout.newTab().setText("Đăng nhập"))
        tabLayout.addTab(binding.tabLayout.newTab().setText("Đăng ký"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = LoginAdapter(this, supportFragmentManager, tabLayout.tabCount)
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