package com.example.persistenz

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager


class SharedPreferencesViewModel(application: Application) : AndroidViewModel(application) {
    // _teaPreference wird nur innerhalb des ViewModels verwendet
   private val _teaPreference = MutableLiveData<String>()
    // teaPreference wird von anderen Klassen verwendet, um auf die Daten zuzugreifen
    var teaPreference: LiveData<String> = _teaPreference

    // SharedPreferences laden
    private val preferences = PreferenceManager.getDefaultSharedPreferences(application)
    // Listener definieren, wecher Änderungen an den Präferenzen beobachtet
    private val preferenceChangeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        if (key == "teaPreferred" || key == "teaWithSugar" || key == "teaSweetener") {
            _teaPreference.value = getTeaPreference(application)
            Log.d("Shared", "Tea preference changed")
        }
    }

    // Methodem, welche die Präferenzen aus den SharedPreferences lädt
    private fun getTeaPreference(application: Application): String {
        val teaPreferred = preferences.getString("teaPreferred", "Keine Auswahl") ?: "Keine Auswahl"
        val sugar: String = if(preferences.getBoolean("teaWithSugar", false)) {
            val sweetenerValue = preferences.getString("teaSweetener", null)
            val sweetenerIndex = application.resources.getStringArray(R.array.teaSweetenerValues).indexOf(sweetenerValue)
            if (sweetenerIndex >= 0) {
                application.resources.getStringArray(R.array.teaSweetener)[sweetenerIndex]
            } else {
                "Standardwert" // oder irgendeinen Fallback-Wert
            }
        } else {
            "ohne Zucker"
        }
        return "Ich trinke am liebsten $teaPreferred, $sugar."
    }

    // Init Block, welcher beim Erstellen des ViewModels ausgeführt wird
    init {
        // Geseicherte Werte aus dem SharedPreferences laden
        _teaPreference.value = getTeaPreference(application)
        // Listener starten, um Änderungen an den Präferenzen zu beobachten
        preferences.registerOnSharedPreferenceChangeListener(preferenceChangeListener)
    }

    override fun onCleared() {
        super.onCleared()
        // Listener beenden, um Speicherlecks zu vermeiden
        preferences.unregisterOnSharedPreferenceChangeListener(preferenceChangeListener)
    }
}
