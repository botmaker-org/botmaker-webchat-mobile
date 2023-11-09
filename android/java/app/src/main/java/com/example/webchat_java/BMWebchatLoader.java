package com.example.webchat_java;


import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class BMWebchatLoader implements IBMWebchatLoader{

    private WebView webView;

    public BMWebchatLoader(WebView webView, String url) {
        this.webView = webView;
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);

        //String htmlContent = "<html><head></head><body><h1>Hello, WebView!</h1></body></html>";
        //String mimeType = "text/html";
        //String encoding = "UTF-8";
        //webView.loadData(htmlContent, mimeType, encoding);

        //localStorage in window
        webView.loadUrl(url);
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
        this.webView.evaluateJavascript(String.format("bmSendMessage('%s');", inputText) , null);
    }
    @Override
    public void bmInfo(final ValueCallback<String> callback){
        this.webView.evaluateJavascript("bmInfo();" , callback);
    }
    @Override
    public void bmConnect(){
        throw new UnsupportedOperationException("Método no implementado");
    }

/*
    bmConnect: async variables => {
        if (variables) window.BOTMAKER_VAR = variables;
        await connect();
        container.start();
    },
    */

    @Override
    public void bmSetVariables(Object variables){
        throw new UnsupportedOperationException("Método no implementado");
    }

/*
    bmSetVariables: variables => {
        variables = _.mapValues(variables, (v, k) => {
            if (v === undefined || v === null) return '';

            if (_.isString(v)) return v.trim();

            if (_.isArray(v) || _.isObject(v)) return JSON.stringify(v);

            return v.toString();
        });
        connectionState.setVariables(variables);
    },
    */

}

