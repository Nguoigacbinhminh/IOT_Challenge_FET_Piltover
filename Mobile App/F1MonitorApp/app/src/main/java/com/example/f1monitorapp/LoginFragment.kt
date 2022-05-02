package com.example.f1monitorapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.f1monitorapp.databinding.FragmentLoginBinding
import com.example.f1monitorapp.databinding.FragmentRegisterBinding

class LoginFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLoginBinding.inflate(layoutInflater)
        binding.loginButton.setOnClickListener {
            val intent = Intent(this@LoginFragment.requireContext(), HomeActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }
}