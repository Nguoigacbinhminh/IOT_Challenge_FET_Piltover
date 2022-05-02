package com.example.f1monitorapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.f1monitorapp.databinding.ActivityPersonInfoBinding

class PersonInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPersonInfoBinding.inflate(layoutInflater)

        binding.saveButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)
    }
}