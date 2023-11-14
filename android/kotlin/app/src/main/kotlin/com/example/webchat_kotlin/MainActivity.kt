package com.example.webchat_kotlin

import android.app.AlertDialog
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val webView = findViewById<WebView>(R.id.webView)
        val BOTMAKER_VAR: MutableMap<String?, String?> = object : HashMap<String?, String?>() {
            init {
                put("firstName", "Lionel")
                put("lastName", "Scaloni")
                put("customVar", "newValueForCustomVar")
                put("userIdOnBusiness", "1")
            }
        }
        val BMWebchat: IBotmakerWebchat = BotmakerWebchat(
            webView,
            "KCRZV8L0G2",
            BOTMAKER_VAR
        )
        val frameLayout = findViewById<LinearLayout>(R.id.layoutInferior)
        val b_maximize = frameLayout.findViewById<Button>(R.id.b_bmMaximize)
        val b_minimize = frameLayout.findViewById<Button>(R.id.b_bmMinimize)
        val b_bmHide = frameLayout.findViewById<Button>(R.id.b_bmHide)
        val b_bmShow = frameLayout.findViewById<Button>(R.id.b_bmShow)
        val b_bmSendMessage = frameLayout.findViewById<Button>(R.id.b_bmSendMessage)
        val b_bmInfo = frameLayout.findViewById<Button>(R.id.b_bmInfo)
        val b_bmSetVariables = frameLayout.findViewById<Button>(R.id.b_bmSetVariables)
        b_maximize.setOnClickListener { BMWebchat.bmMaximize() }
        b_minimize.setOnClickListener { BMWebchat.bmMinimize() }
        b_bmHide.setOnClickListener { BMWebchat.bmHide() }
        b_bmShow.setOnClickListener { BMWebchat.bmShow() }
        b_bmSendMessage.setOnClickListener {
            val messageToSend = "Prueba Send Message"
            BMWebchat.bmSendMessage(messageToSend)
        }
        b_bmInfo.setOnClickListener {
            BMWebchat.bmInfo { value ->
                println(value)
                showAlert(value)
            }
        }
        b_bmSetVariables.setOnClickListener {
            val variables: Map<String?, String?> = object : HashMap<String?, String?>() {
                init {
                    put("variable_prueba", "prueba 1")
                    put("variable_prueba_2", "prueba 2")
                }
            }
            BMWebchat.bmSetVariables(variables)
        }
    }

    private fun showAlert(value: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Info")
            .setMessage(value)
        builder.setPositiveButton("Aceptar") { dialog, id -> dialog.dismiss() }
        val dialog = builder.create()
        dialog.show()
    }
}