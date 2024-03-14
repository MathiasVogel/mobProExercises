package com.example.firstappskeleton

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.firstappskeleton.lifecyclelog.LifecycleLogActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    companion object {
        val QUESTION = "question"
        val ANSWER = "answer"
    }

    private val openQuestionActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            //TODO check if the result is ok and set the content to the textview
            if (result.resultCode == Activity.RESULT_OK) {
                val textViewAnswer = findViewById<TextView>(R.id.main_textView_result) // Ort, an dem die Antwort angezeigt wird
                var answer = resources.getString(R.string.main_text_gotAnswer) + "'" // Text, der vor der Antwort angezeigt wird
                result.data?.let {data: Intent ->
                    data.extras?.let { extras: Bundle ->
                        answer = answer.plus(extras.getString(ANSWER) + "'") // Anhängen der Antwort an den Text
                    }
                }
                textViewAnswer.text = answer // Anzeigen der Antwort
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<Button>(R.id.main_button_logActivity).setOnClickListener { startLogActivity() }
        findViewById<Button>(R.id.main_button_startBrowser).setOnClickListener { startBrowser() }
        findViewById<Button>(R.id.main_button_questionActivity).setOnClickListener { startQuestionActivity() }

    }


    private fun startLogActivity() {
        // TODO: start LifecylceLogActivity mit LifecycleLogFragment
        val logActivityCall = Intent(this, LifecycleLogActivity::class.java) // Angabe der aktuellen Activity (this) und der gewünschten Activity.
        startActivity(logActivityCall) // Starten der gewünschten Activity
    }

    private fun startBrowser() {
        // TODO: start Browser with http://www.hslu.ch
        val browserCall = Intent() // Intent implizit -> Angabe der gewünschten Aktion und Daten
        browserCall.action = Intent.ACTION_VIEW // Aktion: Anzeigen einer Daten
        browserCall.data = Uri.parse("https://www.hslu.ch") // Daten: URI
        startActivity(browserCall)
    }

    private fun startQuestionActivity() {
        // TODO: launch QuestionActivity with Intent
        val questionActivityCall = Intent(this, QuestionActivity::class.java) // Angabe der aktuellen Activity (this) und der gewünschten Activity.
        questionActivityCall.putExtra(QUESTION, "Wieso ist die Welt nicht Falch?") // Angabe des Schlüssels und des Wertes
        openQuestionActivity.launch(questionActivityCall)
    }
}