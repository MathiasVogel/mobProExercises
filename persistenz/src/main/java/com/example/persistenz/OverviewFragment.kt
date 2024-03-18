package com.example.persistenz

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.preference.PreferenceManager

class OverviewFragment: Fragment(R.layout.fragment_overview){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Zugriff auf die Buttons per ID
        val buttonEdit = view.findViewById<Button>(R.id.button_edit_preferences)
        val buttonSet = view.findViewById<Button>(R.id.button_set_default_preferences)
        val buttonSms = view.findViewById<Button>(R.id.button_content_provider)

        // OnClickListener Button Tee-Preferenzen editieren
        buttonEdit.setOnClickListener {
            openTeaPreferenceFragment() // Rufe deine Methode auf.
        }
        // OnClickListener Button Tee-default-Preferenzen setzen
        buttonSet.setOnClickListener {
            setDefaultTeaPreferences()
        }
        // OnClickListener Button SMS anzeigen
        buttonSms.setOnClickListener {
            (activity as MainActivity).showSms()
        }
        //
        val viewModel: SharedPreferencesViewModel by viewModels()
        viewModel.teaPreference.observe(viewLifecycleOwner) { teaPreference ->
            // Aktualisiere die UI entsprechend
            view.findViewById<TextView>(R.id.teaPreferenceTextView).text = teaPreference
        }
    }
    override fun onResume() {
        super.onResume()
        // Aufruf des updateCounter() für die Anzeige des Resume-Counters
        (activity as MainActivity).updateCounter()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Observer beenden, um Speicherlecks zu vermeiden
        val viewModel: SharedPreferencesViewModel by viewModels()
        viewModel.teaPreference.removeObservers(viewLifecycleOwner)
    }

    private fun openTeaPreferenceFragment() {
        // Erstelle ein neues TeaPreferenceFragment
        val teaPreferenceFragment = TeaPreferenceFragment.newInstance()
        // Ersetze das aktuelle Fragment durch das TeaPreferenceFragment
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, teaPreferenceFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setDefaultTeaPreferences() {
        // Zugriff auf die Default SharedPreferences
        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        // Editor instanzieren um die SharedPreferences zu bearbeiten
        val editor = prefs.edit()
        // Setzen der fixen Werte
        editor.putString("teaPreferred", "Grüner Tee")
        editor.putBoolean("teaWithSugar", false)
        // Speichern
        editor.apply()
    }


}