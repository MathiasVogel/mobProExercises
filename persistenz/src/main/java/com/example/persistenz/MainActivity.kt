package com.example.persistenz

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest
import android.provider.Telephony
import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity() {
    companion object {
        const val REQUEST_CODE_READ_SMS = 101 //
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Setze das Layout der Activity, ansosnten würde ein Fehler auftreten
        setContentView(R.layout.activity_main)
        val fragment = OverviewFragment()
        // Das OverviewFragment fragment_container anzeigen
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    fun updateCounter() {
        // Zugriff auf SharedPreferences der MainActivity -> MODE_PRIVATE bedeutet, dass nur diese App auf die Daten zugreifen kann
        val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        // Editor instanzieren um die SharedPreferences zu bearbeiten
        val editor = sharedPreferences.edit()
        // Int Wert mit dem Key "onResumeCount" aus den SharedPreferences laden, falls der Key nicht existiert, wird 0 zurückgegeben
        val currentCount = sharedPreferences.getInt("onResumeCount", 0) + 1
        // Speichern des Wertes
        editor.putInt("onResumeCount", currentCount)
        editor.apply()
        // Aktualisiere die TextView mit dem neuen Wert
        val textView = findViewById<TextView>(R.id.onResume_counter)
        textView.text = "OverviewFragment.onResume() wurde seit der Installation dieser App $currentCount mal aufgerufen."
    }

    fun showSms() {
        // Zuerst prüfen, ob die Berechtigung nicht vorhanden sind
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            // Berechtigungen anfordern
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_SMS), REQUEST_CODE_READ_SMS)
        } else {
            // Falls Berechtigungen vorhanden sind, zeige die SMS in einem AlertDialog
            showSmsInAlertDialog()
        }
    }

    private fun showSmsInAlertDialog() {
        // Liste für Strings
        val smsList = mutableListOf<String>()
        // auf die SMS-Inbox zugreifen und Daten abfragen
        val cursor = contentResolver.query(Telephony.Sms.Inbox.CONTENT_URI, arrayOf(Telephony.Sms.Inbox.BODY), null, null, null)
        // Cursor durchlaufen und die SMS in die Liste hinzufügen
        cursor?.let {
            // Index der Spalten abfragen
            val bodyIndex = it.getColumnIndex(Telephony.Sms.Inbox.BODY)
            if (bodyIndex >= 0) { // Stellen Sie sicher, dass der Index gültig ist
                while (it.moveToNext()) {
                    val smstext = it.getString(bodyIndex)
                    smsList.add(smstext)
                }
            }
            it.close()
        }

        // AkertDialog anzeigen
        // Array aus der Liste erstellen
        val smsArray = smsList.toTypedArray()
        // AlertDialog erstellen
        AlertDialog.Builder(this)
            .setTitle("SMS Inbox")
            .setItems(smsArray, null)
            .setPositiveButton("OK", null)
            .show()
    }


}