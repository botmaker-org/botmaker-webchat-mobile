package com.example.webchat_kotlin

import android.webkit.ValueCallback

interface IBotmakerWebchat {
    /**
     * Maximiza el webchat de Botmaker.
     */
    fun bmMaximize()

    /**
     * Minimiza el webchat de Botmaker.
     */
    fun bmMinimize()

    /**
     * Oculta el webchat de Botmaker.
     */
    fun bmHide()

    /**
     * Visibiliza el webchat de Botmaker.
     */
    fun bmShow()

    /**
     * Envía un mensaje al webchat de botmaker.
     *
     * @param inputText Mensaje a enviar.
     */
    fun bmSendMessage(inputText: String?)

    /**
     * Devuelve informacion del webchat de Botmaker.
     *
     * @param callback para obtener el mensaje de informacion devuelto por el webchat de Botmaker.
     */
    fun bmInfo(callback: ValueCallback<String?>?)

    /**
     * Establece variables del webchat de Botmaker..
     *
     * @param variables Mapa con variables a establecer.
     */
    fun bmSetVariables(variables: Map<String?, String?>?)
}