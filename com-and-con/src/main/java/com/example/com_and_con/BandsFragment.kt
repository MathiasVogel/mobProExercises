package com.example.com_and_con

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class BandsFragment : Fragment(R.layout.fragment_bands){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Zugriff auf die Buttons per ID
        val buttonCommunication = view.findViewById<Button>(R.id.button_communication)
        val buttonRequestServer = view.findViewById<Button>(R.id.button_server_request)
        val buttonResetViewModel = view.findViewById<Button>(R.id.button_reset_view_model)
        val buttonShowBandSelection = view.findViewById<Button>(R.id.button_show_band_selection)

        // OnClickListener definieren
        buttonCommunication.setOnClickListener {

        }
        buttonRequestServer.setOnClickListener {

        }
        buttonResetViewModel.setOnClickListener {

        }
        buttonShowBandSelection.setOnClickListener {

        }

    }
}