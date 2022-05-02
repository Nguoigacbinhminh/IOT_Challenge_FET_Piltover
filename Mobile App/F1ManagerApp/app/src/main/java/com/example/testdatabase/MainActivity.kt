package com.example.testdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.testdatabase.fragments.HomeFragment
import com.example.testdatabase.fragments.ProfileFragment
import com.example.testdatabase.fragments.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var phone: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        phone = intent.getStringExtra("phone_key").toString()

        val homeFragment = HomeFragment()
        val profileFragment = ProfileFragment()
        val settingsFragment = SettingsFragment()

        setCurrentFragment(homeFragment, null)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.homeFragment->setCurrentFragment(homeFragment, null)
                R.id.profileFragment->setCurrentFragment(profileFragment, null)
                R.id.settingsFragment->setCurrentFragment(settingsFragment, null)
            }
            true
        }
    }

   fun setCurrentFragment(fragment: Fragment, position: Int?)=
        supportFragmentManager.beginTransaction().apply {
            val bundle = Bundle()
            if (position != null) {
                bundle.putInt("position", position)
                fragment.arguments = bundle
            }

            replace(R.id.flFragment,fragment)
            addToBackStack(null)
            commit()
        }
}