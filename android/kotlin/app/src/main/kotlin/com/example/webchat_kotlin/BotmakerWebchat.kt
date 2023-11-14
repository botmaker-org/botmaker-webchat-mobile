package com.example.webchat_kotlin

import android.os.Build
import android.util.Log
import android.webkit.ConsoleMessage
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import org.json.JSONObject
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class BotmakerWebchat : IBotmakerWebchat {
    private var webView: WebView? = null
    private var debugger: Boolean? = false

    /**
     * Constructor del webchat de Botmaker.
     *
     * @param webView Contenedor del webchat.
     * @param webchatCode Codigo identificador del webchat.
     * @param BOTMAKER_VAR Variables de configuracion del chat (keys: firstName, lastName, customVar, userIdOnBusiness).
     * @param debugger (true - false) habilita la escucha de logs.
     */
    constructor(
        webView: WebView,
        webchatCode: String?,
        BOTMAKER_VAR: MutableMap<String?, String?>?,
        debugger: Boolean?
    ) {
        this.debugger = debugger
        BotmakerWebchat(webView, webchatCode, BOTMAKER_VAR)
    }

    /**
     * Constructor del webchat de Botmaker.
     *
     * @param webView Contenedor del webchat.
     * @param webchatCode Codigo identificador del webchat.
     * @param BOTMAKER_VAR Variables de configuracion del chat (keys: firstName, lastName, customVar, userIdOnBusiness).
     */
    constructor(
        webView: WebView,
        webchatCode: String?,
        BOTMAKER_VAR: MutableMap<String?, String?>?
    ) {
        try {
            this.webView = webView
            val webSettings = webView.settings
            webSettings.javaScriptEnabled = true
            webSettings.databaseEnabled = true
            webSettings.domStorageEnabled = true
            webSettings.allowFileAccess = true

            /*-----------------------------------
            * Debug Only
            ---------------------------------- */if (debugger!!) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    WebView.setWebContentsDebuggingEnabled(true)
                }
                webView.webChromeClient = object : WebChromeClient() {
                    override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
                        // Imprime el mensaje de la consola en los logs de Android
                        Log.d("WebView Console", consoleMessage.message())
                        return true
                    }
                }
            }
            var botmakerVars = ""
            if (!BOTMAKER_VAR.isNullOrEmpty()) {
                for ((key, value) in BOTMAKER_VAR) {
                    BOTMAKER_VAR[key] = URLEncoder.encode(value, StandardCharsets.UTF_8.toString())
                }
                val jsonObject = JSONObject(BOTMAKER_VAR as Map<String, String>?)
                botmakerVars = String.format("var BOTMAKER_VAR = %s;", jsonObject)
            }
            val htmlContent = String.format(
                "<!DOCTYPE html>" +
                        "<html>" +
                        "<body>" +
                        "<!-- START Botmaker Webchat-->" +
                        "<script>" +
                        "%s" +
                        "(function () {" +
                        "let js = document.createElement('script');" +
                        "js.type = 'text/javascript';" +
                        "js.async = 1;" +
                        "js.src = 'https://go.botmaker.com/rest/webchat/p/%s/init.js';" +
                        "document.body.appendChild(js);" +
                        "})();" +
                        "</script>" +
                        "<!-- END Botmaker Webchat-->" +
                        "</body>" +
                        "</html>",
                botmakerVars,
                URLEncoder.encode(webchatCode, StandardCharsets.UTF_8.toString())
            )
            webView.loadData(htmlContent, "text/html", "UTF-8")
        } catch (e: Exception) {
            Log.e(javaClass.simpleName, e.message!!)
            throw RuntimeException(e)
        }
    }

    override fun bmMaximize() {
        webView!!.evaluateJavascript("bmMaximize();", null)
    }

    override fun bmMinimize() {
        webView!!.evaluateJavascript("bmMinimize();", null)
    }

    override fun bmHide() {
        webView!!.evaluateJavascript("bmHide();", null)
    }

    override fun bmShow() {
        webView!!.evaluateJavascript("bmShow();", null)
    }

    override fun bmSendMessage(inputText: String?) {
        try {
            webView!!.evaluateJavascript(String.format("bmSendMessage('%s');", inputText), null)
        } catch (e: Exception) {
            Log.e(javaClass.simpleName, e.message!!)
        }
    }

    override fun bmInfo(callback: ValueCallback<String?>?) {
        webView!!.evaluateJavascript("bmInfo();", callback)
    }

    override fun bmSetVariables(variables: Map<String?, String?>?) {
        try {
            if (!variables.isNullOrEmpty()) {
                val jsonObject = JSONObject(variables)
                webView!!.evaluateJavascript(String.format("bmSetVariables(%s);", jsonObject), null)
            }
        } catch (e: Exception) {
            Log.e(javaClass.simpleName, e.message!!)
        }
    }
}