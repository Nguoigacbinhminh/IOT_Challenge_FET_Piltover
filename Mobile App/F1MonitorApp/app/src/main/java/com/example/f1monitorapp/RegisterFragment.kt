package com.example.f1monitorapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.f1monitorapp.databinding.FragmentRegisterBinding
import kotlinx.android.synthetic.main.activity_person_info.*

class RegisterFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRegisterBinding.inflate(layoutInflater)
        binding.registerButton.setOnClickListener {
            val intent = Intent(this@RegisterFragment.requireContext(), PersonInfoActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

}