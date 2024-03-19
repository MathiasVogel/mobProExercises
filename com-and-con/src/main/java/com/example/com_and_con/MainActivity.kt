package com.example.com_and_con

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Setze das Layout der Activity, ansosnten würde ein Fehler auftreten
        setContentView(R.layout.activity_main)
        val fragment = BandsFragment()
        // Das OverviewFragment fragment_container anzeigen
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}