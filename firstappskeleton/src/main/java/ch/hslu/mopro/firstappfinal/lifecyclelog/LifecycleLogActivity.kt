package com.example.firstappskeleton.lifecyclelog

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.firstappskeleton.R


class LifecycleLogActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_lifecycle_logger)
		Log.i("hslu_mobApp", "Activity onCreate() aufgerufen")
		if (savedInstanceState == null) {
			//TODO show LifecycleLogFragment
		}
	}

	// TODO: Add further implementions of onX-methods.
}