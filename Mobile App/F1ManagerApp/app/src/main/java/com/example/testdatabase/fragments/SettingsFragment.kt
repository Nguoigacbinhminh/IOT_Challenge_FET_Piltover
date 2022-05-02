package com.example.testdatabase.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testdatabase.LoginActivity
import com.example.testdatabase.MainActivity
import com.example.testdatabase.R
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logout.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        change_password.setOnClickListener {
            val changePasswordFragment = ChangePasswordFragment()
            (activity as MainActivity).setCurrentFragment(changePasswordFragment, null)
        }
        info.setOnClickListener {
            val infoAppFragment = InfoAppFragment()
            (activity as MainActivity).setCurrentFragment(infoAppFragment, null)
        }
    }
}