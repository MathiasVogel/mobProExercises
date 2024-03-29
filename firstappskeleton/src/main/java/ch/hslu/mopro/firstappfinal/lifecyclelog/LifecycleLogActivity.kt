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
			val fragment = LifecycleLogFragment()
			supportFragmentManager.beginTransaction()
				.add(R.id.fragment_host, fragment)
				.commit()
		}
	}

	// TODO: Add further implementions of onX-methods.
	override fun onStart() {
		super.onStart()
		Log.i("hslu_mobApp", "Activity onStart() aufgerufen")
	}

	override fun onRestart() {
		super.onRestart()
		Log.i("hslu_mobApp", "Activity onRestart() aufgerufen")
	}

	override fun onResume() {
		super.onResume()
		Log.i("hslu_mobApp", "Activity onResume() aufgerufen")
	}

	override fun onPause() {
		super.onPause()
		Log.i("hslu_mobApp", "Activity onPause() aufgerufen")
	}

	override fun onStop() {
		super.onStop()
		Log.i("hslu_mobApp", "Activity onStop() aufgerufen")
	}

	override fun onDestroy() {
		super.onDestroy()
		Log.i("hslu_mobApp", "Activity onDestroy() aufgerufen")
	}

}