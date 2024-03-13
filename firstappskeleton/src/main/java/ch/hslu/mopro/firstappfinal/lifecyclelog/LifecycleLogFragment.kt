package com.example.firstappskeleton.lifecyclelog

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.firstappskeleton.R

class LifecycleLogFragment : Fragment(R.layout.fragment_lifecycle_logger) {

	companion object {
		fun newInstance(): LifecycleLogFragment {
			return LifecycleLogFragment()
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		Log.i("hslu_mobApp", "Fragment onCreate() aufgerufen")
	}

	// TODO: Add further implementions of onX-methods.
}
