package com.example.bpstations

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mybpstations.StationListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, StationListFragment())
                .commit()
        }

        val navIcon = findViewById<ImageView>(R.id.navIcon)
        navIcon.setOnClickListener {
            Toast.makeText(this, "Right icon clicked", Toast.LENGTH_SHORT).show()
            // Show filter or open menu
        }
    }
}
