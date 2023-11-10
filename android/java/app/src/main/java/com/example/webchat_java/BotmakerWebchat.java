package com.example.webchat_java;


import android.os.Build;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.Nullable;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class BotmakerWebchat implements IBotmakerWebchat {
    private WebView webView;

    private Boolean debugger = false;

    /**
     * Constructor del webchat de Botmaker.
     *
     * @param webView Contenedor del webchat.
     * @param webchatCode Codigo identificador del webchat.
     * @param BOTMAKER_VAR Variables de configuracion del chat (keys: firstName, lastName, customVar, userIdOnBusiness).
     * @param debugger (true - false) habilita la escucha de logs.
     */
    public BotmakerWebchat(WebView webView, String webchatCode, @Nullable Map<String, String> BOTMAKER_VAR, @Nullable Boolean debugger) {
        this.debugger = debugger;
        new BotmakerWebchat(webView,  webchatCode, BOTMAKER_VAR);
    }
    /**
     * Constructor del webchat de Botmaker.
     *
     * @param webView Contenedor del webchat.
     * @param webchatCode Codigo identificador del webchat.
     * @param BOTMAKER_VAR Variables de configuracion del chat (keys: firstName, lastName, customVar, userIdOnBusiness).
     */
    public BotmakerWebchat(WebView webView, String webchatCode, @Nullable Map<String, String> BOTMAKER_VAR) {
        try {

            this.webView = webView;
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setDatabaseEnabled(true);
            webSettings.setDomStorageEnabled(true);
            webSettings.setAllowFileAccess(true);
    
            /*-----------------------------------
            * Debug Only
            ---------------------------------- */
            if(debugger) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    WebView.setWebContentsDebuggingEnabled(true);
                }
                webView.setWebChromeClient(new WebChromeClient() {
                    @Override
                    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                        // Imprime el mensaje de la consola en los logs de Android
                        android.util.Log.d("WebView Console", consoleMessage.message());
                        return true;
                    }
                });
            }

            String botmakerVars = "";
            if (BOTMAKER_VAR != null && !BOTMAKER_VAR.isEmpty()) {
                for (Map.Entry<String, String> entry : BOTMAKER_VAR.entrySet()) {
                    BOTMAKER_VAR.put(
                        entry.getKey(),
                        URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString())
                    );
                }
                JSONObject jsonObject = new JSONObject(BOTMAKER_VAR);
                botmakerVars = String.format("var BOTMAKER_VAR = %s;", jsonObject);
            }

            String htmlContent = String.format(
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
            );

            webView.loadData(htmlContent, "text/html", "UTF-8");

        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), e.getMessage());
            throw new RuntimeException(e);
        }

    }
    @Override
    public void bmMaximize() {
        this.webView.evaluateJavascript("bmMaximize();", null);
    }
    @Override
    public void bmMinimize() {
        this.webView.evaluateJavascript("bmMinimize();", null);
    }
    @Override
    public void bmHide(){
        this.webView.evaluateJavascript("bmHide();", null);
    }
    @Override
    public void bmShow(){
        this.webView.evaluateJavascript("bmShow();", null);
    }

    @Override
    public void bmSendMessage(String inputText){
        try {
            this.webView.evaluateJavascript(String.format("bmSendMessage('%s');", inputText), null);
        }catch (Exception e){
            Log.e(getClass().getSimpleName(), e.getMessage());
        }
    }

    @Override
    public void bmInfo(final ValueCallback<String> callback){
        this.webView.evaluateJavascript("bmInfo();" , callback);
    }

    @Override
    public void bmSetVariables(Map<String, String> variables){
        try {
            if (variables != null && !variables.isEmpty()){
                JSONObject jsonObject = new JSONObject(variables);
                this.webView.evaluateJavascript(String.format("bmSetVariables(%s);", jsonObject), null);
            }
        }catch (Exception e){
            Log.e(getClass().getSimpleName(), e.getMessage());
        }
    }
}

