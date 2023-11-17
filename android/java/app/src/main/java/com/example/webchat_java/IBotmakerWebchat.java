package com.example.webchat_java;

import android.webkit.ValueCallback;

import java.util.Map;

public interface IBotmakerWebchat {
    /**
     * Maximize Botmaker webchat.
     */
    public void bmMaximize();
    /**
     * Minimize Botmaker webchat.
     */
    public void bmMinimize();
    /**
     * Hide Botmaker webchat.
     */
    public void bmHide();
    /**
     * Show Botmaker webchat.
     */
    public void bmShow();
    /**
     * Send a message to Botmaker webchat.
     *
     * @param inputText Message to send.
     */
    public void bmSendMessage(String inputText);
    /**
     * Returns information from Botmaker webchat.
     *
     * @param callback to get the information message returned by the Botmaker webchat.
     */
    public void bmInfo(final ValueCallback<String> callback);
    /**
     * Set Botmaker webchat variables.
     *
     * @param variables Map with variables to set.
     */
    public void bmSetVariables(Map<String, String> variables);
}
