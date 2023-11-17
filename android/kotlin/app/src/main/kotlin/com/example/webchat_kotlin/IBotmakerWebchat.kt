package com.example.webchat_kotlin

import android.webkit.ValueCallback

interface IBotmakerWebchat {
    /**
     * Maximize Botmaker webchat.
     */
    fun bmMaximize()

    /**
     * Minimize Botmaker webchat.
     */
    fun bmMinimize()

    /**
     * Hide Botmaker webchat.
     */
    fun bmHide()

    /**
     * Show Botmaker webchat.
     */
    fun bmShow()

    /**
     * Send a message to Botmaker webchat.
     *
     * @param inputText Message to send.
     */
    fun bmSendMessage(inputText: String?)

    /**
     * Returns information from Botmaker webchat.
     *
     * @param callback to get the information message returned by the Botmaker webchat.
     */
    fun bmInfo(callback: ValueCallback<String?>?)

    /**
     * Set Botmaker webchat variables.
     *
     * @param variables Map with variables to set.
     */
    fun bmSetVariables(variables: Map<String?, String?>?)
}